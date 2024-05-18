package ru.outeast.wallet_wise.domain.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    // Example
    // @Schema(description = "", example = "")
    // @Size(min = 0, max = 0, message = "")
    // @NotBlank(message = "Никнейм не может быть пустым")
    // private void fieldName;

    @Schema(description = "Никнейм", example = "user")
    private String nickname;

    @Schema(description = "Имя", example = "Олег")
    private String name;

    @Schema(description = "Фамилия", example = "Олегов")
    private String surname;

    @Schema(description = "User Email", example = "mail@mail.mail")
    private String mail;
}
