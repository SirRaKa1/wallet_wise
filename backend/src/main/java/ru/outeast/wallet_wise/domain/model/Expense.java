package ru.outeast.wallet_wise.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "expenses")
public class Expense {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "wallet_id")
    @JsonIgnore
    private Wallet wallet;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;


}
