package ru.outeast.wallet_wise.exception;

import org.springframework.http.HttpStatus;

public class UserDoesNotExistException extends CustomHttpException{
    public UserDoesNotExistException() {
        super("Пользователя не существует", HttpStatus.NOT_FOUND);
    }
}
