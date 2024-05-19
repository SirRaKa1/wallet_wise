package ru.outeast.wallet_wise.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Wallet info without user data and id")
public class WalletInfo {
    @Schema(description = "wallet name", example = "wallet name")
    private String name;
    @Schema(description = "wallet balance", example = "2.9")
    private Float balance;
}
