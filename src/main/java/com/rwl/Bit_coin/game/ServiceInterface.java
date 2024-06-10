package com.rwl.Bit_coin.game;

import com.rwl.Bit_coin.dtos.UserGameDto;
import com.rwl.Bit_coin.entity.Game;

import java.util.List;

public interface ServiceInterface {

    //returns two lists containing games by type system generated clubs and own generated clubs
    List<List<UserGameDto>> findGamesByUserId(Long userId);
}
