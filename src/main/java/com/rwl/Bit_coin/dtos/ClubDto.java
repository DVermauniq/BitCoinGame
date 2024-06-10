package com.rwl.Bit_coin.dtos;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubDto {

    private Long clubId;
    private String clubName;

    }
