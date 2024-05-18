package ru.outeast.wallet_wise.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {
    @Schema(description = "Никнейм", example = "user")
    @Size(min = 5, max = 50, message = "Никнейм должен содержать от 5 до 50 символов")
    @NotBlank(message = "Никнейм не может быть пустым")
    private String nickname;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    @Schema(description = "Имя", example = "Олег")
    @Size(max = 50, message = "Имя пользователя должно содержать до 50 символов")
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Schema(description = "Фамилия", example = "Олегов")
    @Size(max = 50, message = "Фамилия пользователя должна содержать до 50 символов")
    @NotBlank(message = "Фамилия не может быть пустой")
    private String surname;
    // private SendUser user;
    
    @Schema(description = "User Email", example = "mail@mail.mail")
    @Size(max = 50, message = "User mail must contain no more than 50 characters")
    @NotBlank(message = "Mail can not be empty")
    private String mail;
}
