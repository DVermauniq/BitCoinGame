package com.rwl.Bit_coin.dtos;

import com.rwl.Bit_coin.enumm.GameDuration;
import com.rwl.Bit_coin.enumm.GameStatus;
import com.rwl.Bit_coin.enumm.GameType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserGameDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long gameId;
    @Enumerated(value = EnumType.STRING)
    private GameType gameType;
    private Long numberOfPlayers;
    private Double totalAmountCollected;
}
