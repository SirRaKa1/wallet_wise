package ru.outeast.wallet_wise.exception;

import org.springframework.http.HttpStatus;

public class SignInException extends CustomHttpException {
    public SignInException() {
        super("Неправильный логин и/или пароль", HttpStatus.BAD_REQUEST);
    }
}
