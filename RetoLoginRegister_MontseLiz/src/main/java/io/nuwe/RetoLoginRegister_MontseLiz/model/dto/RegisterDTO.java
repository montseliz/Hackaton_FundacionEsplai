package io.nuwe.RetoLoginRegister_MontseLiz.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @Schema(description = "Name of the user to register", example = "montse")
    private String name;

    @Schema(description = "Email of the user to register", example = "montse@gmail.com")
    private String email;

    @Schema(description = "Password of the user to register", example = "password123")
    private String password;

}
