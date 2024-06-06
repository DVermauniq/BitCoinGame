package com.rwl.Bit_coin.service;

import com.rwl.Bit_coin.entity.User;

public interface WalletService {
    public void addMoney(int amount);
    public String CheckBalance(User userId);
}
