package ru.outeast.wallet_wise.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TargetCreate {
    @Schema(description = "Target Name", example = "Target Name")
    @Size(min = 1, max = 50, message = "The target name should be between one and fifty characters long")
    @NotBlank(message = "Name of target should exist")
    private String name;

    @Schema(description = "Target start balance", example = "0f")
    private Float balance;

    @Schema(description = "Target goal", example = "0f")
    @NotNull(message = "Target must not be null")
    private Float targetCost;
}
