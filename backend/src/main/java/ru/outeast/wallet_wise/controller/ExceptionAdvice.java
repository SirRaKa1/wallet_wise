package ru.outeast.wallet_wise.controller;


import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.outeast.wallet_wise.exception.CustomHttpException;
import ru.outeast.wallet_wise.exception.UserExistsException;


@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        if (e.getCause() instanceof CustomHttpException)
            return new ResponseEntity<>(e.getCause().getMessage(), ((CustomHttpException) e.getCause()).getHttpStatus());
        throw e;
    }

    @ExceptionHandler(CustomHttpException.class)
    public ResponseEntity<String> handleException(CustomHttpException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }



}
