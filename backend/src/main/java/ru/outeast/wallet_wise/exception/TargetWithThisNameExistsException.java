package ru.outeast.wallet_wise.exception;

import org.springframework.http.HttpStatus;

public class TargetWithThisNameExistsException extends CustomHttpException {
    public TargetWithThisNameExistsException(){
        super("User already has target with such name", HttpStatus.BAD_REQUEST);
    }
}
