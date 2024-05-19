package ru.outeast.wallet_wise.service;

import ru.outeast.wallet_wise.domain.dto.request.TargetCreate;
import ru.outeast.wallet_wise.domain.dto.request.TargetUpdate;
import ru.outeast.wallet_wise.domain.dto.response.TargetInfo;
import ru.outeast.wallet_wise.exception.TargetWithThisNameExistsException;
import ru.outeast.wallet_wise.domain.model.Target;
import ru.outeast.wallet_wise.exception.TargetDoesNotExistException;
import ru.outeast.wallet_wise.exception.UserDoesNotExistException;

import java.util.List;
import java.util.UUID;

public interface TargetService {
    List<TargetInfo> GetUserTargets(UUID userId);

    Target getById(UUID id) throws TargetDoesNotExistException;

    Target create(UUID userId, TargetCreate targetBody) throws UserDoesNotExistException, TargetWithThisNameExistsException;

    Target save(Target target);

    void delete(UUID userId, UUID id) throws TargetDoesNotExistException;

    Target update(UUID id, UUID userId, TargetUpdate targetBody) throws TargetDoesNotExistException;
}
