package ru.outeast.wallet_wise.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TargetAfterCreate {
    @Schema(description = "target id", format = "UUID")
    private UUID id;

    @Schema(description = "target name", example = "target name")
    private String name;

    @Schema(description = "target balance", example = "2.9")
    private Float balance;

    @Schema(description = "target balance", example = "2.9")
    private Float targetGoal;

}
