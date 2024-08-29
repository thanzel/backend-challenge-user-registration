package com.application.user.dto;

import com.application.user.advice.anotation.ValidRegexPassword;
import com.application.user.entities.Phone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpsertDTO {

    UUID id;

    @NotEmpty(message = "El nombre es requerido")
    String name;

    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@dominio\\.cl$",
            message = "El email debe tener el formato 'aaaaaaa@dominio.cl'"
    )
    @NotEmpty(message = "El email es obligatorio")
    // @Email(message = "El email es incorrecto")
    String email;

    LocalDateTime created;
    LocalDateTime modified;
    LocalDateTime lastLogin;
    Boolean isActive;

    List<Phone> phones;

    @NotBlank
    @ValidRegexPassword
    String password;

    String token;
}
