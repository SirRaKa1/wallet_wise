package ru.outeast.wallet_wise.domain.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String nickname;

    private String password;
}
