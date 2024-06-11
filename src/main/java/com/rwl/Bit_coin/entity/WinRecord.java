package com.rwl.Bit_coin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recordId;
    private Double winAmount;
    private LocalDate date;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Game game;


}
