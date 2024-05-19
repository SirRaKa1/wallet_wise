package ru.outeast.wallet_wise.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outeast.wallet_wise.domain.model.Income;

import java.util.UUID;

public interface IncomeRepository extends JpaRepository<Income, UUID> {
}
