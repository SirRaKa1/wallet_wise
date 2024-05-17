package ru.outeast.wallet_wise.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "wallet")
    private List<Category> categories;

    @OneToMany(mappedBy = "wallet")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "wallet")
    private List<Income> incomes;
}
