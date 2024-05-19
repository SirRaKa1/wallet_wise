package ru.outeast.wallet_wise.service;

import ru.outeast.wallet_wise.domain.dto.request.WalletCreate;
import ru.outeast.wallet_wise.domain.dto.response.WalletInfo;
import ru.outeast.wallet_wise.domain.model.Wallet;
import ru.outeast.wallet_wise.exception.UserDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletWithThisNameExistsException;

import java.util.List;
import java.util.UUID;

public interface WalletService {

    public Wallet save(Wallet wallet);

    public List<Wallet> createDefaults(UUID userId);

    public Wallet create(UUID userId, WalletCreate walletBody) throws WalletWithThisNameExistsException, UserDoesNotExistException;

    public void delete(UUID userId, UUID id) throws WalletDoesNotExistException;

    // List<Wallet> GetCurrentUserWallets();

    List<WalletInfo> GetUserWallets(UUID uuid);

    public Wallet getById(UUID uuid) throws WalletDoesNotExistException;

    // Wallet updateWallet(Wallet wallet) throws WalletDoesNotExistException;

    // void delete(Wallet wallet);
}
