package com.application.user.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {
    /**
     * Método: Captura los errores según las anotaciones de validaciones que se disparan con el @Valid del controller y las anotaciones del dto por atributo
     * @param exception de argumento not valid
     * @return una lista de errores
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerUnexpectedArguments(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach( error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    /**
     * Captura excepciones de tipo IllegalArgumentException.
     * @param exception de argumento not válid
     * @return un mapa de errores con el mensaje de la excepción.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
