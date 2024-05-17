package ru.outeast.wallet_wise.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.outeast.wallet_wise.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);
}
