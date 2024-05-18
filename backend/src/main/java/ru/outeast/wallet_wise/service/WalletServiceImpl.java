package ru.outeast.wallet_wise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import ru.outeast.wallet_wise.domain.dto.request.WalletCreate;
import ru.outeast.wallet_wise.domain.model.User;
import ru.outeast.wallet_wise.domain.model.Wallet;
import ru.outeast.wallet_wise.exception.UserDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletWithThisNameExistsException;
import ru.outeast.wallet_wise.repository.UserRepository;
import ru.outeast.wallet_wise.repository.WalletRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    public Wallet create(UUID userId, WalletCreate walletBody)
            throws WalletWithThisNameExistsException, UserDoesNotExistException {
        User user = userRepository.findById(userId).orElseThrow(UserDoesNotExistException::new);
        Boolean isWalletExists = walletRepository.exists(
                Example.of(Wallet.builder().user(user).name(walletBody.getName()).build()));
        if (isWalletExists)
            throw new WalletWithThisNameExistsException();
        Wallet wallet = new Wallet();
        wallet.setId(UUID.randomUUID());
        wallet.setBalance(walletBody.getBalance());
        wallet.setName(walletBody.getName());
        wallet.setUser(user);
        return save(wallet);
    }

    private Wallet createByNameAndUser(String name, User user) {
        Wallet wallet = Wallet.builder().id(UUID.randomUUID()).balance(0f).name(name).user(user).build();
        return wallet;
    }

    @Override
    public List<Wallet> createDefaults(UUID userId) {
        List<Wallet> wallets = new ArrayList<Wallet>();
        User user = User.builder().id(userId).build();
        Wallet cards = createByNameAndUser("Карты", user);
        wallets.add(cards);
        Wallet cash = createByNameAndUser("Наличные", user);
        wallets.add(cash);
        return walletRepository.saveAll(wallets);
    }

    @Override
    public Wallet save(Wallet wallet) {
    return walletRepository.save(wallet);
    }

    // @Override
    // public List<Wallet> GetCurrentUserWallets() {
    // return walletRepository.findAll(
    // Example.of(
    // Wallet.builder().user(
    // User.builder()
    // .nickname(SecurityContextHolder.getContext().getAuthentication().getName())
    // .build())
    // .build()));
    // }

    // @Override
    // public Wallet getById(UUID uuid) throws WalletDoesNotExistException {
    // return
    // walletRepository.findById(uuid).orElseThrow(WalletDoesNotExistException::new);
    // }

    // @Override
    // public Wallet updateWallet(Wallet wallet) throws WalletDoesNotExistException
    // {
    // if (!walletRepository.existsById(wallet.getId()))
    // throw new WalletDoesNotExistException();
    // return save(wallet);
    // }

    // @Override
    // public void delete(Wallet wallet) {
    // walletRepository.delete(wallet);
    // }

}
