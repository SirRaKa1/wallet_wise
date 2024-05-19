package ru.outeast.wallet_wise.exception;

import org.springframework.http.HttpStatus;

public class WalletWithThisNameExistsException extends CustomHttpException {

    public WalletWithThisNameExistsException() {
        super("User already has wallet with such name", HttpStatus.BAD_REQUEST);
    }
}
