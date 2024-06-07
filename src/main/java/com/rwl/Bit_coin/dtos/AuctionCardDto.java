package com.rwl.Bit_coin.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionCardDto {
    private Long gameId;
    private Integer amountPerPerson;
    private Integer noOfPeople;
    private LocalDate Date;
    private String password;
}
