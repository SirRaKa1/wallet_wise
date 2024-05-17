package ru.outeast.wallet_wise.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallets")
public class Wallet {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "balance")
    private Float balance;

    @Column(name = "name")
    private String name;

    // TODO: belongsTo(User, FK: owner, onDelete: "CASCADE")
}
