package com.application.user.advice.anotation;

import com.application.user.advice.validator.ValidPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * CREACIÓN DE UNA NUEVA ANOTACIÓN PARA VALIDAR EL PATRÓN DEL PASSWORD
 * RECORDAR QUE EL PATRÓN ES CONFIGURABLE (VER APPLICATION.PROPERTIES)
 */

@Documented //metadata de spring para el javadoc
@Constraint(validatedBy = ValidPasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME) //cuando se ejecute la app se creará esta anotación
@Target({ ElementType.FIELD, ElementType.METHOD}) //se puede usar esta anotación en campos y métodos
public @interface ValidRegexPassword {
    String message() default "Contraseña no válida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
