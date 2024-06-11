package com.rwl.Bit_coin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long walletId;
    private double balance;
    @OneToMany(mappedBy = "wallet",cascade = CascadeType.ALL)
    private List<BankDetails> bankDetails;
    @OneToOne
    private User user;
}
