package ru.outeast.wallet_wise.service;


import ru.outeast.wallet_wise.domain.model.User;

import java.util.UUID;

public interface UserService {

    public User save(User user);

    public User create(User user) throws Exception;

    public User getByNickname(String nickname);

    public User getCurrentUser();

    public User getById(UUID id);

    //public User updateCurrentUser(SendUser user);

    public User updateCurrentUser(User user);

    void delete(User user);
}
