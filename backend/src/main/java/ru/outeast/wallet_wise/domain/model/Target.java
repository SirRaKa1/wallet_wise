package ru.outeast.wallet_wise.domain.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "targetCost")
    private Float targetCost;

    @Column(name = "balance")
    private Float balance;

    @OneToMany(mappedBy = "target")
    private List<Income> incomes;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

}
