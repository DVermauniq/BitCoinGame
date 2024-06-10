package com.rwl.Bit_coin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bankId;
    @Column(unique = true)
    private String accountNumber;
    private String ifscCode;
    private String passbookUrl;
    private String paySlipUrl;
    @ManyToOne
    @JoinColumn
    private WalletTransactions wallet;

}
