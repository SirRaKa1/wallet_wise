package ru.outeast.wallet_wise.exception;

import org.springframework.http.HttpStatus;

public class WalletWithThisNameExistsException extends CustomHttpException{

    public WalletWithThisNameExistsException() {
        super("Кошелёк с таким именем уже существует", HttpStatus.NOT_MODIFIED);
    }
}
