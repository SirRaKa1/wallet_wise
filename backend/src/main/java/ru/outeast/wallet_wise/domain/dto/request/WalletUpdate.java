package ru.outeast.wallet_wise.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WalletUpdate {
    // Example
    // @Schema(description = "", example = "")
    // @Size(min = 0, max = 0, message = "")
    // @NotBlank(message = "Никнейм не может быть пустым")
    // private void fieldName;

    @Schema(description = "Wallet Name", example = "Wallet Name")
    @Size(min = 1, max = 50, message = "The wallet name should be between one and fifty characters long")
    private String name;


    @Schema(description = "Wallet start balance", example = "0f")
    private Float balance;
}
