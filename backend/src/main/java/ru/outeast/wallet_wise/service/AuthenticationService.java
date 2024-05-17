package ru.outeast.wallet_wise.service;


import ru.outeast.wallet_wise.domain.dto.JwtAuthenticationResponse;
import ru.outeast.wallet_wise.domain.dto.SignInRequest;
import ru.outeast.wallet_wise.domain.dto.SignUpRequest;
import ru.outeast.wallet_wise.exception.SignInException;
import ru.outeast.wallet_wise.exception.UserExistsException;

public interface AuthenticationService {
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws UserExistsException;

    public JwtAuthenticationResponse signIn(SignInRequest request) throws SignInException;
}
