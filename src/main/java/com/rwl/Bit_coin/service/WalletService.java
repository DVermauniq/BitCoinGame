package com.rwl.Bit_coin.service;

import com.rwl.Bit_coin.entity.User;

public interface WalletService {

    void addMoney(int amount);

    String CheckBalance(User userId);
}
