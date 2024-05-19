package ru.outeast.wallet_wise.service;


import ru.outeast.wallet_wise.domain.dto.request.SignInRequest;
import ru.outeast.wallet_wise.domain.dto.request.SignUpRequest;
import ru.outeast.wallet_wise.domain.dto.response.JwtAuthenticationResponse;
import ru.outeast.wallet_wise.exception.SignInException;
import ru.outeast.wallet_wise.exception.UserExistsException;

import java.util.Map;

public interface AuthenticationService {
    public Map<String, String> signUp(SignUpRequest request) throws UserExistsException;

    public JwtAuthenticationResponse signIn(SignInRequest request) throws SignInException;
}
