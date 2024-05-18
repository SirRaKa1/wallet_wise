package ru.outeast.wallet_wise.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.outeast.wallet_wise.domain.dto.*;
import ru.outeast.wallet_wise.domain.model.User;
import ru.outeast.wallet_wise.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Операции пользователя")
public class UserController {
    private final UserService userService;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @Operation(summary = "Получить информацию о пользователе")
    public User getInfo() {
        return userService.getCurrentUser();
    }


}
