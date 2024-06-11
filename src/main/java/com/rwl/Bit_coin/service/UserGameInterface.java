package com.rwl.Bit_coin.service;


import com.rwl.Bit_coin.entity.Game;

public interface UserGameInterface {

    Game enterInGame(Long userId, Long gameId);

    void startGame(int amount, String password);
}
