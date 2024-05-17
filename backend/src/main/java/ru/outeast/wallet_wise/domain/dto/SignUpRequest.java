package ru.outeast.wallet_wise.domain.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String nickname;

    private String password;

    private String name;

    private String surname;
    // private SendUser user;

}
