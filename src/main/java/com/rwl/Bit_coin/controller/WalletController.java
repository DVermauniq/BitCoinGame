package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.serviceImplementation.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletServiceImpl walletService;

    @PostMapping("/add-amount")
    public ResponseEntity<String> addAmountToWallet(@RequestParam Long userId, @RequestParam Double winAmount) {
        walletService.addAmountToWallet(userId, winAmount);
        return ResponseEntity.ok("Amount added to wallet successfully");
    }

}
