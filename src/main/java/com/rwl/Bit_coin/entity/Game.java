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
    @Enumerated(value = EnumType.STRING)
    private GameType gameType;
    private Long numberOfPlayers;
    private Double totalAmountCollected;
    private Double amountPerPerson;
    private LocalDate date;
    @Enumerated(value = EnumType.STRING)
    private GameDuration gameDuration;
    @Enumerated(value = EnumType.STRING)
    private GameStatus gameStatus;
    private List<Long> winnerListByOrder;
    @ManyToMany(mappedBy = "games",cascade = CascadeType.ALL)

    @JoinTable(
            name = "user_game",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
     private List<User> users;

    
    @ManyToMany(mappedBy = "gameList",cascade = CascadeType.ALL)
    private List<Club> clubList;


}
