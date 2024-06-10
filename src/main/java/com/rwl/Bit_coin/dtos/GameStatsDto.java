package com.rwl.Bit_coin.dtos;

import com.rwl.Bit_coin.enumm.ClubType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameStatsDto {
    private String firstName;
    private String lastName;
    private int totalGames;
    @Enumerated(EnumType.STRING)
    private ClubType clubType;
    private double completedBitcoins;
    private double totalWinnings;
}
