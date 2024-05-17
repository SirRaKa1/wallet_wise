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
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    // TODO: what about bitmap?
    @Column(name = "icon")
    private String icon;

    // TODO: belongsTo(Wallet, FK: wallet, onDelete: "CASCADE")
    // TODO: belongsTo(User, FK: owner, onDelete: "CASCADE")
}
