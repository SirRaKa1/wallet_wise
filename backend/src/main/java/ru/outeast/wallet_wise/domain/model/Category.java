package ru.outeast.wallet_wise.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /*// TODO: what about bitmap?
    @Column(name = "icon", columnDefinition = "BitMap")
    private byte[] icon;*/

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "walletId")
    @JsonIgnore
    private Wallet wallet;

}
