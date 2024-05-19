package ru.outeast.wallet_wise.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outeast.wallet_wise.domain.model.Target;

import java.util.UUID;

public interface TargetRepository extends JpaRepository<Target, UUID> {
}
