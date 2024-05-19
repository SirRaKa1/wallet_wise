package ru.outeast.wallet_wise.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.outeast.wallet_wise.domain.dto.request.WalletCreate;
import ru.outeast.wallet_wise.domain.dto.request.WalletUpdate;
import ru.outeast.wallet_wise.domain.dto.response.WalletAfterCreate;
import ru.outeast.wallet_wise.domain.dto.response.WalletInfo;
import ru.outeast.wallet_wise.domain.model.Wallet;
import ru.outeast.wallet_wise.exception.UserDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletWithThisNameExistsException;
import ru.outeast.wallet_wise.service.WalletService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@Tag(name = "Wallet")
public class WalletController {
    private final WalletService walletService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping()
    @Operation(summary = "Create wallet")
    @ResponseStatus(HttpStatus.CREATED)
    public WalletAfterCreate createWallet(@RequestBody @Valid WalletCreate walletBody)
            throws WalletWithThisNameExistsException, UserDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Wallet wallet = walletService.create(userId, walletBody);
        return new WalletAfterCreate(wallet.getId(), wallet.getBalance(), wallet.getName());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete wallet")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWallet(@PathVariable(name = "id") UUID id) throws WalletDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        walletService.delete(userId,id);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @Operation(summary = "Get all user's wallets")
    @ResponseStatus(HttpStatus.OK)
    public List<WalletInfo> getWallets(){
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return walletService.GetUserWallets(userId);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/{id}")
    @Operation(summary = "Get wallet")
    @ResponseStatus(HttpStatus.OK)
    public Wallet getWallet(@PathVariable(name = "id") UUID id) throws WalletDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Wallet wallet = walletService.getById(id);
        if (!wallet.getUser().getId().equals(userId))
            throw new WalletDoesNotExistException();
        return wallet;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update wallet")
    @ResponseStatus(HttpStatus.OK)
    public WalletAfterCreate updateWallet(@PathVariable(name = "id") UUID id, @RequestBody @Valid WalletUpdate walletBody) throws WalletDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Wallet wallet = walletService.update(id, userId, walletBody);
        return new WalletAfterCreate(wallet.getId(), wallet.getBalance(), wallet.getName());
    }


}
