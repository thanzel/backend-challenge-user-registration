package com.application.user.advice.validator;

import com.application.user.advice.anotation.ValidRegexPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Pattern;

/**
 * Configuración de la validación del password (Patrón Regex)
 */
public class ValidPasswordValidator implements ConstraintValidator<ValidRegexPassword, String> {

    @Value("${password.regex}")
    private Pattern passwordPattern ;

    @Override
    public void initialize(ValidRegexPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        return value.matches(String.valueOf(passwordPattern)); //Valida el regex del Password
    }
}
