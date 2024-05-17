package ru.outeast.wallet_wise.service;


import ru.outeast.wallet_wise.domain.dto.JwtAuthenticationResponse;
import ru.outeast.wallet_wise.domain.dto.SignInRequest;
import ru.outeast.wallet_wise.domain.dto.SignUpRequest;

public interface AuthenticationService {
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws Exception;

    public JwtAuthenticationResponse signIn(SignInRequest request) ;
}
