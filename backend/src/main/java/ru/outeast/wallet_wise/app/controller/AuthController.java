package ru.outeast.wallet_wise.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.outeast.wallet_wise.app.common.KafkaSender;
import ru.outeast.wallet_wise.domain.dto.request.SignInRequest;
import ru.outeast.wallet_wise.domain.dto.request.SignUpRequest;
import ru.outeast.wallet_wise.domain.dto.response.JwtAuthenticationResponse;
import ru.outeast.wallet_wise.exception.SignInException;
import ru.outeast.wallet_wise.exception.UserExistsException;
import ru.outeast.wallet_wise.service.AuthenticationService;
import ru.outeast.wallet_wise.service.WalletService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final KafkaSender kafkaSender;
    private final WalletService walletService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) throws UserExistsException {
        Map<String, String> authInfo = authenticationService.signUp(request);
        kafkaSender.sendMessage("Пользователь вошёл", "auth_topic");
        walletService.createDefaults(UUID.fromString(authInfo.get("userId")));
        return new JwtAuthenticationResponse(authInfo.get("jwt"));

    }
    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) throws SignInException {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signIn(request);
        kafkaSender.sendMessage("Пользователь вошёл", "auth_topic");
        return jwtAuthenticationResponse;
    }

}
