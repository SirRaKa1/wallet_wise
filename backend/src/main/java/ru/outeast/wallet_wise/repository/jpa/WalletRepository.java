package ru.outeast.wallet_wise.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.outeast.wallet_wise.domain.dto.response.WalletInfo;
import ru.outeast.wallet_wise.domain.model.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    @Query("SELECT new ru.outeast.wallet_wise.domain.dto.response.WalletInfo(w.name, w.balance) " +
            "FROM Wallet w " +
            "WHERE w.user.id = :userId")
    List<WalletInfo> findWalletInfosByUserId(@Param("userId") UUID userId);

    @Query("SELECT new ru.outeast.wallet_wise.domain.dto.response.WalletInfo('Total Balance', CAST(SUM(w.balance) AS float)) " +
            "FROM Wallet w " +
            "WHERE w.user.id = :userId")
    WalletInfo findTotalBalanceByUserId(@Param("userId") UUID userId);


}
