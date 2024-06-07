package com.rwl.Bit_coin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String firstName;
    private String lastName;
    @Size(max = 10)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 50)
    @Email
    private String email;
    @Size(max = 120)
    private String password;
    private LocalDate dob;
    @Size(max = 12, min = 12)
    private String aadharNo;
    private String aadharUrl;
    private String imageUrl;
    private String signatureUrl;
    private String referralCode;
    private boolean Eliminated;
    private boolean Winner;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private BankDetails bankDetails;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Query> queries;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WalletTransactions> walletTransactions;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Game> games;
//    @ManyToMany(mappedBy = "users")
//    @JoinTable(
//            name = "user_game",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "game_id")
//    )
    private List<Club> clubList;
}
