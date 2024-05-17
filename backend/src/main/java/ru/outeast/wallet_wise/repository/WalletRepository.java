package ru.outeast.wallet_wise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.outeast.wallet_wise.domain.model.Wallet;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
