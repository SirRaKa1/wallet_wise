package ru.outeast.wallet_wise.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.outeast.wallet_wise.domain.dto.response.TargetInfo;
import ru.outeast.wallet_wise.domain.model.Target;

import java.util.List;
import java.util.UUID;

public interface TargetRepository extends JpaRepository<Target, UUID> {
    @Query("SELECT new ru.outeast.wallet_wise.domain.dto.response.TargetInfo(t.name, t.targetCost, t.balance)" +
            "FROM Target t " +
            "WHERE t.user.id = :userId")
    List<TargetInfo> findTargetInfosByUserId(@Param("userId") UUID userId);
}
