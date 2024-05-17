package ru.outeast.wallet_wise.controller;


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
public class AuthController {
    private final AuthenticationService authenticationService;


    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) throws UserExistsException {
        return authenticationService.signUp(request);
    }


    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) throws SignInException {
        return authenticationService.signIn(request);
    }

}
