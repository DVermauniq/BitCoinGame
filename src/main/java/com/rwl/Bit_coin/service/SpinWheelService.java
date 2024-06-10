package com.rwl.Bit_coin.service;

import java.util.List;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;

public interface SpinWheelService {

	Game enterInGame(Long userId, Long gameId);

	String spinWheel(Long gameId);

	String getWinner(Long gameId);

	List<User> getActiveUsers(Game games);

	Game getGameById(Long gameId);

}
