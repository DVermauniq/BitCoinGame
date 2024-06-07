package com.rwl.Bit_coin.entity;

import com.rwl.Bit_coin.enumm.TransactionStatus;
import com.rwl.Bit_coin.enumm.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long walletId;
    @Transient
    private static double totalBalance;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private Double transactionAmount;
    private LocalDate transactionDate;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn
    private Game game;
}
