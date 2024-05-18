package ru.outeast.wallet_wise.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.outeast.wallet_wise.exception.CustomHttpException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        if (e.getCause() instanceof CustomHttpException)
            return new ResponseEntity<>(e.getCause().getMessage(),
                    ((CustomHttpException) e.getCause()).getHttpStatus());
        throw e;
    }

    @ExceptionHandler(CustomHttpException.class)
    public ResponseEntity<String> handleException(CustomHttpException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            if (!errors.containsKey(fieldName)) {
                errors.put(fieldName, new ArrayList<>());
            }
            errors.get(fieldName).add(errorMessage);
        });

        return new ResponseEntity<>(errors, ex.getStatusCode());
    }

}
