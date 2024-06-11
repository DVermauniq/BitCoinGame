package com.rwl.Bit_coin.service;


import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.WalletTransactions;

public interface UserGameInterface {

    Game enterInGame( Long userId, Long gameId);
    WalletTransactions startGame(Long gameId, Long amount, String password);

}
