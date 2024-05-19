package ru.outeast.wallet_wise.exception;


import org.springframework.http.HttpStatus;

public class UserExistsException extends CustomHttpException {
    public UserExistsException() {
        super("Пользователь с таким логином существует",HttpStatus.CONFLICT);
    }
}
