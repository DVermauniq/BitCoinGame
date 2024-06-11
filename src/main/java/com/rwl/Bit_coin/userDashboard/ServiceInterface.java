package com.rwl.Bit_coin.userDashboard;

import com.rwl.Bit_coin.dtos.UserGameDto;

import java.util.List;

public interface ServiceInterface {

    //returns two lists containing games by type system generated clubs and own generated clubs
    List<List<UserGameDto>> findGamesByUserId(Long userId);

    Integer countCompletedBcByUserId(Long userId);
}
