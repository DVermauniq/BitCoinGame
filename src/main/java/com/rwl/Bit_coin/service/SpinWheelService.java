package com.rwl.Bit_coin.service;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;

public interface SpinWheelService {

	Game enterInGame(Long userId, Long gameId);

	String spinWheel(Long gameId);

	String declareWinner(User user, Game game);

	User getMonthlyWinner(Long gameId, int month);

//	User sendWinningPrice(User user, double prizeamount);

}
