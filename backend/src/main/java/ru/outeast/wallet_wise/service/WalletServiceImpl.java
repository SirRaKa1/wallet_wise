package ru.outeast.wallet_wise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import ru.outeast.wallet_wise.domain.model.User;
import ru.outeast.wallet_wise.domain.model.Wallet;
import ru.outeast.wallet_wise.exception.WalletDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletWithThisNameExistsException;
import ru.outeast.wallet_wise.repository.WalletRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Wallet create(Wallet wallet) throws WalletWithThisNameExistsException {
        if (walletRepository.exists(
                Example.of(Wallet.builder().user(wallet.getUser()).name(wallet.getName()).build())
        ))
            throw new WalletWithThisNameExistsException();
        return save(wallet);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public List<Wallet> GetCurrentUserWallets(){
        return walletRepository.findAll(
                Example.of(
                        Wallet.builder().user(
                                User.builder().nickname(SecurityContextHolder.getContext().getAuthentication().getName()).build()
                        ).build()));
    }

    @Override
    public Wallet getById(UUID uuid) throws WalletDoesNotExistException {
        return walletRepository.findById(uuid).orElseThrow(WalletDoesNotExistException::new);
    }

    @Override
    public Wallet updateWallet(Wallet wallet) throws WalletDoesNotExistException {
        if (!walletRepository.existsById(wallet.getId()))
            throw new WalletDoesNotExistException();
        return save(wallet);
    }

    @Override
    public void delete(Wallet wallet){
        walletRepository.delete(wallet);
    }

}
