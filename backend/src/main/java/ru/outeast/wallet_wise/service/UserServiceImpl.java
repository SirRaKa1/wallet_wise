package ru.outeast.wallet_wise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.outeast.wallet_wise.domain.dto.UserDto;
import ru.outeast.wallet_wise.domain.model.User;
import ru.outeast.wallet_wise.exception.UserDoesNotExistException;
import ru.outeast.wallet_wise.exception.UserExistsException;
import ru.outeast.wallet_wise.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User create(User user) throws UserExistsException {
        if (getByNickname(user.getNickname()) != null)
            throw new UserExistsException();
        return save(user);
    }

    @Override
    public User getByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public User getCurrentUser() {
        return getByNickname(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(UserDto userBody, UUID userId) throws UserDoesNotExistException {
        User user = userRepository.findById(userId).orElseThrow(UserDoesNotExistException::new);
        if (userBody.getNickname() != null)
            user.setNickname(userBody.getNickname());
        if (userBody.getMail() != null)
            user.setMail(userBody.getMail());
        if (userBody.getName() != null)
            user.setName(userBody.getName());
        if (userBody.getSurname() != null)
            user.setSurname(userBody.getSurname());

        return save(user);
    }

    @Override
    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }

}
