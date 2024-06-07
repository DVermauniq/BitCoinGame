package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.entity.Game;

import com.rwl.Bit_coin.service.SpinWheelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spinwheel")
public class SpinWheelController {

	@Autowired
	private SpinWheelService spinWheelService;

	@PostMapping("/enter")
	public Game enterGame(@RequestParam Long userId, @RequestParam Long gameId) {
		return spinWheelService.enterInGame(userId, gameId);
	}

	@PostMapping("/spin")
	public String spinWheel(@RequestParam Long gameId) {
		return spinWheelService.spinWheel(gameId);
	}

	@GetMapping("/winner")
	public String getWinner(@RequestParam Long gameId) {
		return spinWheelService.getWinner(gameId);
	}
}
