package com.application.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneDTO {

    Long id;

    @NotBlank(message = "El número de teléfono es requerido")
    String number;
    String cityCode;
    String countryCode;
}
