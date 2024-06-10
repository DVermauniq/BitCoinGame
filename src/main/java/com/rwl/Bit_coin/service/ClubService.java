package com.rwl.Bit_coin.service;

import com.rwl.Bit_coin.dtos.ClubDto;
import com.rwl.Bit_coin.dtos.UserDto;
import com.rwl.Bit_coin.entity.Club;

public interface ClubService {
    Club createClub(ClubDto clubDto);
    Club addUserToClub(Long clubId, UserDto userDTO);
}
