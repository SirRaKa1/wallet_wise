package ru.outeast.wallet_wise.service;

import java.util.UUID;

import ru.outeast.wallet_wise.domain.model.User;

public interface JwtService {
    public String generateToken(User user);

    public boolean isTokenValid(String token);

    public UUID extractUserId(String token);

}