package ru.outeast.wallet_wise.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.outeast.wallet_wise.domain.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByNickname(String nickname);
}
