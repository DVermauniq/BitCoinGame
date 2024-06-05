package com.rwl.Bit_coin.entity;

import com.rwl.Bit_coin.enumm.GameDuration;
import com.rwl.Bit_coin.enumm.GameType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long gameId;
    @Enumerated(value = EnumType.STRING)
    private GameType gameType;
    private Long numberOfPlayers;
    private Double totalAmountCollected;
    private Double amountPerPerson;
    @Enumerated(value = EnumType.STRING)
    private GameDuration gameDuration;
    @ManyToMany(mappedBy = "games",cascade = CascadeType.ALL)
    @JoinColumn
    private List<User> users;


}
