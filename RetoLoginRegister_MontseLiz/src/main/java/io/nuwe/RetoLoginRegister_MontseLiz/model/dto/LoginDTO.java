package io.nuwe.RetoLoginRegister_MontseLiz.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @Schema(description = "Email of the user to log in", example = "montse@gmail.com")
    private String email;

    @Schema(description = "Password of the user to log in", example = "montse@gmail.com")
    private String password;

}
