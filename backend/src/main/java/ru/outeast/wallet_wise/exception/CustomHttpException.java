package ru.outeast.wallet_wise.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class CustomHttpException extends Exception{
    private final HttpStatus httpStatus;

    public CustomHttpException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
