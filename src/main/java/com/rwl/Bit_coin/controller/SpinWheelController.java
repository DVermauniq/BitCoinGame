package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.service.SpinWheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spin-wheel")
public class SpinWheelController {

    @Autowired
    private SpinWheelService spinWheelService;

    @PostMapping("/enter-game")
    public Game enterGame(@RequestParam Long userId, @RequestParam Long gameId) {
        return spinWheelService.enterInGame(userId, gameId);
    }

    @PostMapping("/spin")
    public String spinWheel(@RequestParam Long gameId) {
        return spinWheelService.spinWheel(gameId);
    }

    @GetMapping("/monthly-winner")
    public User getMonthlyWinner(@RequestParam Long gameId, @RequestParam int month) {
        return spinWheelService.getMonthlyWinner(gameId, month);
    }
}
