package ru.outeast.wallet_wise.exception;

import org.springframework.http.HttpStatus;

public class TargetDoesNotExistException extends CustomHttpException{
    public TargetDoesNotExistException(){
        super("Target has not found", HttpStatus.NOT_FOUND);
    }
}
