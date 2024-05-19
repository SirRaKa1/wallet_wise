package ru.outeast.wallet_wise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.outeast.wallet_wise.domain.dto.request.TargetCreate;
import ru.outeast.wallet_wise.domain.dto.request.TargetUpdate;
import ru.outeast.wallet_wise.domain.dto.response.TargetInfo;
import ru.outeast.wallet_wise.exception.TargetWithThisNameExistsException;
import ru.outeast.wallet_wise.domain.model.Target;
import ru.outeast.wallet_wise.domain.model.User;
import ru.outeast.wallet_wise.exception.TargetDoesNotExistException;
import ru.outeast.wallet_wise.exception.UserDoesNotExistException;
import ru.outeast.wallet_wise.repository.jpa.TargetRepository;
import ru.outeast.wallet_wise.repository.jpa.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TargetServiceImpl implements TargetService {
    private final TargetRepository targetRepository;
    private final UserRepository userRepository;

    @Override
    public List<TargetInfo> GetUserTargets(UUID userId) {
        return targetRepository.findTargetInfosByUserId(userId);
    }

    @Override
    public Target getById(UUID id) throws TargetDoesNotExistException {
        return targetRepository.findById(id).orElseThrow(TargetDoesNotExistException::new);
    }

    @Override
    public Target create(UUID userId, TargetCreate targetBody)
            throws UserDoesNotExistException, TargetWithThisNameExistsException {
        User user = userRepository.findById(userId).orElseThrow(UserDoesNotExistException::new);
        if (targetRepository.exists(
                Example.of(Target.builder().user(user).name(targetBody.getName()).build())
        ))
            throw new TargetWithThisNameExistsException();
        Target target = Target.builder().
                id(UUID.randomUUID()).name(targetBody.getName())
                .targetCost(targetBody.getTargetCost()).user(user).build();
        if (targetBody.getBalance() == null) target.setBalance(0f);
        else target.setBalance(targetBody.getBalance());
        return save(target);
    }

    @Override
    public Target save(Target target) {
        return targetRepository.save(target);
    }

    @Override
    public void delete(UUID userId, UUID id) throws TargetDoesNotExistException {
        Target target = getById(id);
        if (target.getUser().getId().equals(userId))
            targetRepository.delete(target);
        else
            throw new TargetDoesNotExistException();
    }

    @Override
    public Target update(UUID id, UUID userId, TargetUpdate targetBody) throws TargetDoesNotExistException {
        Target target = getById(id);
        if (!target.getUser().getId().equals(userId))
            throw new TargetDoesNotExistException();
        if (targetBody.getName()!=null)
            target.setName(targetBody.getName());
        if (targetBody.getBalance()!=null)
            target.setBalance(targetBody.getBalance());
        if (targetBody.getTargetCost()!=null)
            target.setTargetCost(targetBody.getTargetCost());
        return save(target);
    }
}
