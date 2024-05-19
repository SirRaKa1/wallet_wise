package ru.outeast.wallet_wise.exception;

import org.springframework.http.HttpStatus;

public class WalletDoesNotExistException extends CustomHttpException{
    public WalletDoesNotExistException(){
        super("Кошелька не существует", HttpStatus.NOT_FOUND);
    }
}
