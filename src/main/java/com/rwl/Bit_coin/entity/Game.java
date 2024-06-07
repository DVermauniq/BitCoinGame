package com.rwl.Bit_coin.entity;

import com.rwl.Bit_coin.enumm.GameDuration;
import com.rwl.Bit_coin.enumm.GameStatus;
import com.rwl.Bit_coin.enumm.GameType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long gameId;
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    private Long numberOfPlayers;
    private Double totalAmountCollected;
    private Double amountPerPerson;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private GameDuration gameDuration;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;
    private List<Long> winnerListByOrder;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Club club;
}
