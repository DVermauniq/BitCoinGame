package com.rwl.Bit_coin.service;



import com.rwl.Bit_coin.entity.Game;


public interface SpinWheelService {

    Game enterInGame(Long userId, Long gameId);
    String spinWheel(Long gameId);
    String getWinner(Long gameId); 


}
