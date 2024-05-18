package ru.outeast.wallet_wise.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    // Example
    // @Schema(description = "", example = "")
    // @Size(min = 0, max = 0, message = "")
    // @NotBlank(message = "Никнейм не может быть пустым")
    // private void fieldName;

    @Schema(description = "Никнейм", example = "nickname")
    @Size(min = 5, max = 50, message = "Никнейм должен содержать от 5 до 50 символов")
    private String nickname;

    @Schema(description = "Имя", example = "Олег")
    @Size(max = 50, message = "Имя пользователя должно содержать до 50 символов")
    private String name;

    @Schema(description = "Фамилия", example = "Олегов")
    @Size(max = 50, message = "Фамилия пользователя должна содержать до 50 символов")
    private String surname;

    @Schema(description = "User Email", example = "mail@mail.mail")
    @Size(max = 50, message = "User mail must contain no more than 50 characters")
    @Email(message = "Mail must use format mail@mail.mail")
    private String mail;
}
