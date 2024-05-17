package ru.outeast.wallet_wise.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.outeast.wallet_wise.domain.model.User;

public interface JwtService {
    public String generateToken(User user);
    public boolean isTokenValid(String token, UserDetails userDetails);
    public String extractUserLogin(String token);




}