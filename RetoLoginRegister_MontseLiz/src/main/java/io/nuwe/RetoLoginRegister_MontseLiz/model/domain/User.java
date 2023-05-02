package io.nuwe.RetoLoginRegister_MontseLiz.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    @Schema(description = "Identifier of the user", example = "643d909f15da8348ee4805c1")
    private ObjectId id;

    @NotBlank
    @Field(name = "name")
    @Schema(description = "Name of the user", example = "Montse")
    private String name;

    @CreationTimestamp
    @Field(name = "registration_date")
    @Schema(description = "Registration date of the user", example = "2023-04-10 18:46:38.227499")
    private LocalDateTime registration;

    @Email
    @NotBlank
    @Field(name = "email")
    @Schema(description = "Email of the user", example = "montse@gmail.com")
    private String email;

    @NotBlank
    @Field(name = "password")
    @Schema(description = "Password of the user", example = "password123")
    private String password;

    @Field(name = "role")
    @Schema(description = "Role of the user", example = "USER")
    private Role role;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registration = LocalDateTime.now();
    }

}
