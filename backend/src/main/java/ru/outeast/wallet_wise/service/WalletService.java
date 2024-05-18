package ru.outeast.wallet_wise.service;

import ru.outeast.wallet_wise.domain.model.Wallet;
import ru.outeast.wallet_wise.exception.WalletDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletWithThisNameExistsException;

import java.util.List;
import java.util.UUID;

public interface WalletService {
    public Wallet create (Wallet wallet) throws WalletWithThisNameExistsException;
    public Wallet save (Wallet wallet);

    List<Wallet> GetCurrentUserWallets();

    Wallet getById(UUID uuid) throws WalletDoesNotExistException;

    Wallet updateWallet(Wallet wallet) throws WalletDoesNotExistException;

    void delete(Wallet wallet);
}
