package com.rwl.Bit_coin.dtos;

import com.rwl.Bit_coin.enumm.ClubType;
import com.rwl.Bit_coin.enumm.GameType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class UserGameDto {
    private Long gameId;
    private ClubType clubType;
    private Long numberOfPlayers;
    private Double totalAmountCollected;
    private LocalDate startDate;
}
