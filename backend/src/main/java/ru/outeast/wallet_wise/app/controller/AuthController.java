package ru.outeast.wallet_wise.app.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final KafkaSender kafkaSender;


    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) throws UserExistsException {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signUp(request);
        kafkaSender.sendMessage("Пользователь вошёл","auth_topic");
        return jwtAuthenticationResponse;
    }


    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) throws SignInException {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signIn(request);
        kafkaSender.sendMessage("Пользователь вошёл","auth_topic");
        return jwtAuthenticationResponse;
    }

}