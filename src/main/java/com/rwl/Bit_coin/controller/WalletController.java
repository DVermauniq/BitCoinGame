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

    @PostMapping("/{gameId}/win")
    public ResponseEntity<Void> declareWinner(@PathVariable Long gameId,@RequestParam Double winAmount) {
        walletService.addAmountToWallet(gameId,winAmount);
        return ResponseEntity.ok().build();
    }

}
