package ru.outeast.wallet_wise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.outeast.wallet_wise.domain.dto.request.SignInRequest;
import ru.outeast.wallet_wise.domain.dto.request.SignUpRequest;
import ru.outeast.wallet_wise.domain.dto.response.JwtAuthenticationResponse;
import ru.outeast.wallet_wise.domain.model.User;
import ru.outeast.wallet_wise.exception.SignInException;
import ru.outeast.wallet_wise.exception.UserExistsException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> signUp(SignUpRequest request) throws UserExistsException {
        User user = new User();
        UUID userId = UUID.randomUUID();
        // user.getDataFromSendUser(request.getUser());
        user.setNickname(request.getNickname());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setId(userId);
        user.setMail(request.getMail());
        // user.setRole("ROLE_USER");

        if (userService.create(user) != null) {
            var jwt = jwtService.generateToken(user);
            Map<String, String> responseMap = new HashMap<String, String>();
            responseMap.put("jwt", jwt);
            responseMap.put("userId", userId.toString());
            return responseMap;
        }
        return null;
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) throws SignInException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getNickname(),
                    request.getPassword()));
        } catch (Exception ignored) {
            throw new SignInException();
        }

        var jwt = jwtService.generateToken(userService.getByNickname(request.getNickname()));
        return new JwtAuthenticationResponse(jwt);
    }
}
