package com.application.user.dto;

import com.application.user.entities.Phone;
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
public class UserDTO {

    UUID id;
    String name;
    String email;
    LocalDateTime created;
    LocalDateTime modified;
    LocalDateTime lastLogin;
    Boolean isActive;
    List<Phone> phones;

}
