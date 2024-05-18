package ru.outeast.wallet_wise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.outeast.wallet_wise.domain.dto.*;
import ru.outeast.wallet_wise.domain.model.User;
import ru.outeast.wallet_wise.exception.UserDoesNotExistException;
import ru.outeast.wallet_wise.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Операции пользователя")
public class UserController {
    private final UserService userService;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/me")
    @Operation(summary = "Get self-data")
    @ResponseStatus(HttpStatus.OK)
    public User getMe() {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return userService.getById(userId);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "patch self-data")
    public User updateUser(@RequestBody @Valid UserDto userBody) throws UserDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return userService.updateUser(userBody, userId);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/me")
    @Operation(summary = "delete self-data")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSelfData() throws UserDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        userService.delete(userId);
    }

}
