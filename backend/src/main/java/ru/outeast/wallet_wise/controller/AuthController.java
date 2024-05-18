package ru.outeast.wallet_wise.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.outeast.wallet_wise.domain.dto.JwtAuthenticationResponse;
import ru.outeast.wallet_wise.domain.dto.SignInRequest;
import ru.outeast.wallet_wise.domain.dto.SignUpRequest;
import ru.outeast.wallet_wise.exception.SignInException;
import ru.outeast.wallet_wise.exception.UserExistsException;
import ru.outeast.wallet_wise.service.AuthenticationService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;


    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) throws UserExistsException {
        return authenticationService.signUp(request);
    }


    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) throws SignInException {
        return authenticationService.signIn(request);
    }

}
