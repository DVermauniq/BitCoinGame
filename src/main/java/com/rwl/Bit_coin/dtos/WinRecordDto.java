package com.rwl.Bit_coin.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinRecordDto {
    private Long recordId;
    private Double winAmount;
    private LocalDate date;
    private String firstName;
    private String lastName;
    private Long gameId;
}
