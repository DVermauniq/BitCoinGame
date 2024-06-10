package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.service.SpinWheelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spinwheel")
public class SpinWheelController {

	@Autowired
	private SpinWheelService spinWheelService;

	@Autowired

//	@PostMapping("/enter")
//	public Game enterGame(@RequestParam Long userId, @RequestParam Long gameId) {
//		return spinWheelService.enterInGame(userId, gameId);
//	}

	@PostMapping("/spin")
	public String spinWheel(@RequestParam Long gameId) {
		return spinWheelService.spinWheel(gameId);
	}

	@GetMapping("/winner")
	public String getWinner(@RequestParam Long gameId) {
		return spinWheelService.getWinner(gameId);
	}

	@GetMapping("/{gameId}/active-users")
	public List<User> getActiveUsers(@PathVariable("gameId") Long gameId) {
		Game game = spinWheelService.getGameById(gameId);
		if (game == null) {
			// Handle case where game is not found
			return null;
		}
		return spinWheelService.getActiveUsers(game);
	}

}
