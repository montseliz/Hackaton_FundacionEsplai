package io.nuwe.RetoLoginRegister_MontseLiz.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nuwe.RetoLoginRegister_MontseLiz.model.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    @JsonIgnore
    @Schema(description = "Identifier of the user registered", example = "643d909f15da8348ee4805c1")
    private ObjectId id;

    @Schema(description = "Name of the user registered", example = "montse")
    private String name;

    @Schema(description = "Registration date of the user", example = "2023-04-10 18:46:38.227499")
    private LocalDateTime registration;

    @Schema(description = "Email of the user registered", example = "montse@gmail.com")
    private String email;

    @JsonIgnore
    @Schema(description = "Password of the user registered", example = "password123")
    private String password;

    @Schema(description = "Role of the user registered", example = "USER")
    private Role role;

}
