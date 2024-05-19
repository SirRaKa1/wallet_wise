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
@Schema(description = "Target info without user data and id")
public class TargetInfo {

    @Schema(description = "target name", example = "target name")
    private String name;

    @Schema(description = "target cost", example = "2.9")
    private Float targetCost;

    @Schema(description = "target balance", example = "2.9")
    private Float balance;
}
