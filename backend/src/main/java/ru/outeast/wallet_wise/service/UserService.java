package ru.outeast.wallet_wise.service;


import ru.outeast.wallet_wise.domain.model.User;

public interface UserService {

    public User save(User user);

    public User create(User user) throws Exception;

    public User getByNickname(String nickname);

    public User getCurrentUser();

    public User getById(long id);

    //public User updateCurrentUser(SendUser user);

    public User updateCurrentUser(User user);

    void delete(User user);
}
