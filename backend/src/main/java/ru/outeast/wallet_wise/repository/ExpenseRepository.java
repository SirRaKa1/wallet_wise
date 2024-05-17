package ru.outeast.wallet_wise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outeast.wallet_wise.domain.model.Expense;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
