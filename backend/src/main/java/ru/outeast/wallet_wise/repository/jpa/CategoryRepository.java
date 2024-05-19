package ru.outeast.wallet_wise.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outeast.wallet_wise.domain.model.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
