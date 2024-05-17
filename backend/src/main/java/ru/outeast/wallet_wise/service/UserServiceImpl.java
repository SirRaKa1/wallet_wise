package ru.outeast.wallet_wise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.outeast.wallet_wise.domain.model.User;
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

    /*
     * @Override
     * public User updateCurrentUser(SendUser user) {
     * User oldUser = getCurrentUser();
     * delete(oldUser);
     * oldUser.getDataFromSendUser(user);
     * return save(oldUser);
     * }
     */

    @Override
    public User updateCurrentUser(User user) {
        userRepository.delete(user);
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

}
