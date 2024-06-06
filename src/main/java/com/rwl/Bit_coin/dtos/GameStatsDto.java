package com.rwl.Bit_coin.dtos;

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
    private int systemGeneratedClubs;
    private int privateClubs;
    private double completedBitcoins;
    private double totalWinnings;
}
