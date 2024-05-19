package ru.outeast.wallet_wise.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.outeast.wallet_wise.domain.dto.request.TargetCreate;
import ru.outeast.wallet_wise.domain.dto.request.TargetUpdate;
import ru.outeast.wallet_wise.domain.dto.request.WalletUpdate;
import ru.outeast.wallet_wise.domain.dto.response.TargetAfterCreate;
import ru.outeast.wallet_wise.domain.dto.response.TargetInfo;
import ru.outeast.wallet_wise.domain.dto.response.WalletAfterCreate;
import ru.outeast.wallet_wise.domain.model.Target;
import ru.outeast.wallet_wise.domain.model.Wallet;
import ru.outeast.wallet_wise.exception.*;
import ru.outeast.wallet_wise.service.TargetService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/target")
@RequiredArgsConstructor
@Tag(name = "Target operation")
public class TargetController {
    private final TargetService targetService;
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    @Operation(summary = "Get all targets")
    @ResponseStatus(HttpStatus.OK)
    public List<TargetInfo>getTargets(){
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return targetService.GetUserTargets(userId);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/{id}")
    @Operation(summary = "Get target")
    @ResponseStatus(HttpStatus.OK)
    public Target getTarget(@PathVariable(name = "id") UUID id) throws TargetDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Target target = targetService.getById(id);
        if (!target.getUser().getId().equals(userId))
            throw new TargetDoesNotExistException();
        return target;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping()
    @Operation(summary = "Create target")
    @ResponseStatus(HttpStatus.CREATED)
    public TargetAfterCreate createTarget(@RequestBody @Valid TargetCreate targetBody)
            throws UserDoesNotExistException, TargetWithThisNameExistsException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Target target = targetService.create(userId, targetBody);
        return new TargetAfterCreate(target.getId(), target.getName(), target.getBalance(), target.getTargetCost());
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete target")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTarget(@PathVariable(name = "id") UUID id) throws TargetDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        targetService.delete(userId,id);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update target")
    @ResponseStatus(HttpStatus.OK)
    public TargetAfterCreate updateTarget(@PathVariable(name = "id") UUID id, @RequestBody @Valid TargetUpdate targetBody) throws TargetDoesNotExistException {
        UUID userId = (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Target target = targetService.update(id, userId, targetBody);
        return new TargetAfterCreate(target.getId(), target.getName(), target.getBalance(), target.getTargetCost());
    }
}
