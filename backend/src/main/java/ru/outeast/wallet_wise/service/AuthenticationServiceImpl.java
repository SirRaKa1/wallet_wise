package ru.outeast.wallet_wise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.outeast.wallet_wise.domain.dto.JwtAuthenticationResponse;
import ru.outeast.wallet_wise.domain.dto.SignUpRequest;
import ru.outeast.wallet_wise.domain.dto.SignInRequest;
import ru.outeast.wallet_wise.domain.model.User;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws Exception {
        var user = new User();
        //user.getDataFromSendUser(request.getUser());
        user.setNickname(request.getNickname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setId(UUID.randomUUID());
        //user.setRole("ROLE_USER");

        if (userService.create(user) != null) {
            var jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponse(jwt);
        }
        return null;
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getNickname(),
                    request.getPassword()
            ));
        }
        catch (Exception ignored){
            throw new RuntimeException();
        }

        var jwt = jwtService.generateToken(userService.getByNickname(request.getNickname()));
        return new JwtAuthenticationResponse(jwt);
    }
}