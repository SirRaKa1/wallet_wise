package ru.outeast.wallet_wise.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.outeast.wallet_wise.domain.dto.request.WalletCreate;
import ru.outeast.wallet_wise.domain.dto.response.WalletAfterCreate;
import ru.outeast.wallet_wise.domain.model.Wallet;
import ru.outeast.wallet_wise.exception.UserDoesNotExistException;
import ru.outeast.wallet_wise.exception.WalletWithThisNameExistsException;
import ru.outeast.wallet_wise.service.WalletService;

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

}
