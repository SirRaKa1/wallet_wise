package ru.outeast.wallet_wise.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incomes")
public class Income {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "date")
    private Date date;
    // TODO: belongsTo(Wallet, FK: wallet, onDelete: "CASCADE")
    // TODO: belongsTo(Target, FK: target, onDelete: "set NULL")
}
