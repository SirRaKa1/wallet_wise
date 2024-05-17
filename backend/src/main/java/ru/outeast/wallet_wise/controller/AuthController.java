package ru.outeast.wallet_wise.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.outeast.wallet_wise.domain.dto.JwtAuthenticationResponse;
import ru.outeast.wallet_wise.domain.dto.SignInRequest;
import ru.outeast.wallet_wise.domain.dto.SignUpRequest;
import ru.outeast.wallet_wise.service.AuthenticationService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;


    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) throws Exception {
        return authenticationService.signUp(request);
    }


    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }

}
