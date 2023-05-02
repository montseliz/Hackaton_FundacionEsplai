package io.nuwe.RetoLoginRegister_MontseLiz.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Field(name = "name")
    @Schema(description = "Name of the role", example = "USER")
    private String name;
    
}
