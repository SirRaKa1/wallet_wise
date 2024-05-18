package ru.outeast.wallet_wise.domain.dto.response;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Wallet Response without user data")
public class WalletAfterCreate {
    @Schema(description = "wallet id", format = "UUID")
    private UUID id;

    @Schema(description = "wallet balance", example = "2.9")
    private Float balance;

    @Schema(description = "wallet name", example = "wallet name")
    private String name;
}
