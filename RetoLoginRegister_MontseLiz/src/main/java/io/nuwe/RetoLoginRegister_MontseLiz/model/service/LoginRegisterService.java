package io.nuwe.RetoLoginRegister_MontseLiz.model.service;

import io.nuwe.RetoLoginRegister_MontseLiz.model.domain.Role;
import io.nuwe.RetoLoginRegister_MontseLiz.model.domain.User;
import io.nuwe.RetoLoginRegister_MontseLiz.model.dto.AuthenticationResponseDTO;
import io.nuwe.RetoLoginRegister_MontseLiz.model.dto.LoginDTO;
import io.nuwe.RetoLoginRegister_MontseLiz.model.dto.RegisterDTO;
import io.nuwe.RetoLoginRegister_MontseLiz.model.dto.RegisterUserDTO;
import io.nuwe.RetoLoginRegister_MontseLiz.model.exception.EmailDuplicatedException;
import io.nuwe.RetoLoginRegister_MontseLiz.model.exception.PasswordIncorrectException;
import io.nuwe.RetoLoginRegister_MontseLiz.model.exception.UserDuplicatedException;
import io.nuwe.RetoLoginRegister_MontseLiz.model.exception.UserNotFoundException;
import io.nuwe.RetoLoginRegister_MontseLiz.model.repository.IUserRepository;
import io.nuwe.RetoLoginRegister_MontseLiz.security.JwtGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginRegisterService {

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public LoginRegisterService(ModelMapper modelMapper, AuthenticationManager authenticationManager, IUserRepository userRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    /**
     * Method to create a user register in the database. It is used in the register() method of the LoginRegisterController.
     * @param registerDTO
     * @return RegisterUserDTO
     */
    public RegisterUserDTO createRegister(RegisterDTO registerDTO) {

        RegisterDTO registerEmailValidated = validateRegisterEmail(registerDTO);
        RegisterDTO registerEmailAndNameValidated = validateRegisterName(registerEmailValidated);

        User user = new User(registerEmailAndNameValidated.getName(), registerEmailAndNameValidated.getEmail(), passwordEncoder.encode(registerEmailAndNameValidated.getPassword()));

        assignRoleToUser(user);
        userRepository.save(user);

        return convertUserToDTO(user);
    }

    /**
     * Method to authenticate the user. It is used in the login() method of the LoginRegisterController.
     * @param loginDTO
     * @return AuthenticationResponseDTO
     */
    public AuthenticationResponseDTO authenticateLogin (LoginDTO loginDTO) {
        LoginDTO loginDTOValidated = validateLoginEmailAndPassword(loginDTO);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTOValidated.getEmail(), loginDTOValidated.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        if (!jwtGenerator.isTokenValid(token)) {
            throw new AuthenticationCredentialsNotFoundException("Token generated is not valid");
        }
        return new AuthenticationResponseDTO(loginDTOValidated.getEmail(), passwordEncoder.encode(loginDTOValidated.getPassword()), token);
    }

    /**
     * Method to retrieve all users stored in the database. It is used in the users() method of the LoginRegisterController.
     * @return List<RegisterUserDTO>
     */
    public List<RegisterUserDTO> getAllUsers() {
        if (userRepository.findAll().isEmpty()) {
            throw new UserNotFoundException("There are no users introduced in the database");
        } else {
            List<User> usersFromDB = userRepository.findAll();

            return convertUserListToDTO(usersFromDB);
        }
    }

    /**
     * Internal method to check if the email of the User to be registered is unique, so that it is not repeated in the database.
     * Used in the createRegister() method.
     */
    private RegisterDTO validateRegisterEmail (RegisterDTO registerDTO) {

        if(userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new EmailDuplicatedException("Email introduced already exists");
        } else {
            return registerDTO;
        }
    }

    /**
     * Internal method to check if the name of the User to be registered is unique, so that it is not repeated in the database.
     * Used in the createRegister() method.
     */
    private RegisterDTO validateRegisterName (RegisterDTO registerDTO) {

        if(userRepository.existsByName(registerDTO.getName())) {
            throw new UserDuplicatedException("User's name must be unique");
        } else {
            return registerDTO;
        }
    }

    /**
     * Internal method to assign the role "USER" to a user. Used in the createRegister() method.
     */
    private void assignRoleToUser(User user) {

        Role role;
        Optional<User> userRole = userRepository.findByRole("USER");

        if(userRole.isPresent()) {
            role = userRole.get().getRole();
        } else {
            role = new Role("USER");
        }
        user.setRole(role);
    }

    /**
     * Internal method to validate the email and password in the database. It is used in the authenticateLogin() method.
     */
    private LoginDTO validateLoginEmailAndPassword (LoginDTO loginDTO) {

        RegisterUserDTO registerUserDTO = findEmailInDB(loginDTO);
        String encodedPassword = registerUserDTO.getPassword();
        String password = loginDTO.getPassword();

        if (passwordEncoder.matches(password, encodedPassword)) {
            return loginDTO;
        } else {
            throw new PasswordIncorrectException("Password incorrect, please try again");
        }
    }

    /**
     * Internal method to find a user by its email in the database. Used in the validateLoginEmailAndPassword() method.
     */
    private RegisterUserDTO findEmailInDB (LoginDTO loginDTO) {

        User user;
        Optional<User> emailFromDB = userRepository.findByEmail(loginDTO.getEmail());

        if (emailFromDB.isPresent()) {
            user = emailFromDB.get();
        } else {
            throw new UserNotFoundException("User's email not found or incorrect");
        }

        return convertUserToDTO(user);
    }

    /**
     * Internal method for converting a user to a DTO. Used in the createRegister() and findEmailInDB methods.
     */
    private RegisterUserDTO convertUserToDTO (User user) {
        return modelMapper.map(user, RegisterUserDTO.class);
    }

    /**
     * Internal method for converting all users to a DTO list. Used in the createRegister() method.
     */
    private List<RegisterUserDTO> convertUserListToDTO(List<User> users) {
        return users.stream().map(this::convertUserToDTO).collect(Collectors.toList());
    }

}
