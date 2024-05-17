package ru.outeast.wallet_wise.domain.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "targets")
public class Target {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "target-cost")
    private Float targetCost;

    @Column(name = "balance")
    private Float balance;

    // TODO: belongsTo(User, FK: owner, onDelete: "CASCADE")
}
