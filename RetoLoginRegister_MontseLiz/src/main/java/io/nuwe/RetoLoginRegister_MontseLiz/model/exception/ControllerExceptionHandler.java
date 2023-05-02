package io.nuwe.RetoLoginRegister_MontseLiz.model.exception;

import io.nuwe.RetoLoginRegister_MontseLiz.model.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PasswordIncorrectException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Message> passwordIncorrectExceptionHandler (PasswordIncorrectException exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Message> userNotFoundExceptionHandler(UserNotFoundException exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Message> authenticationCredentialsNotFoundExceptionHandler(AuthenticationCredentialsNotFoundException exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDuplicatedException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Message> userDuplicatedExceptionHandler(UserDuplicatedException exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.CONFLICT.value(), LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailDuplicatedException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Message> emailDuplicatedExceptionHandler (EmailDuplicatedException exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.CONFLICT.value(), LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Message> internalServerErrorExceptionHandler(Exception exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
