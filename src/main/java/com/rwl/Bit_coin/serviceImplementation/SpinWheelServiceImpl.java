package com.rwl.Bit_coin.serviceImplementation;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.enumm.GameStatus;
import com.rwl.Bit_coin.repo.GameRepository;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.SpinWheelService;

@Service
public class SpinWheelServiceImpl implements SpinWheelService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GameRepository gameRepository;

	private Random random = new Random();
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	@Override
	public Game enterInGame(Long userId, Long gameId) {
		Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
		List<User> entries = game.getUser();
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// Ensure only 12 users can enter the game
		if (entries.size() >= 12) {
			throw new RuntimeException("The game already has 12 participants");
		}

		// Prevent duplicate entries
		if (!entries.contains(user)) {
			entries.add(user);
			game.setUser(entries);
			gameRepository.save(game);
		}

		return game;
	}

	@Override
	public String spinWheel(Long gameId) {
		Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));

		if (game.getWinnerListByOrder().size() >= 12) {
			return "All 12 winners have already been determined";
		}

		// Schedule spin wheel to run once a month
		scheduler.scheduleAtFixedRate(() -> {
			spinAndDeclareWinner(game);
		}, 0, 1, TimeUnit.MILLISECONDS);

		return "Spin wheel scheduled to run monthly for the next 12 months";
	}

	private List<User> getActiveUsers(Game game) {
		return game.getUser().stream().filter(user -> !user.isEliminated()).collect(Collectors.toList());
	}

	private void spinAndDeclareWinner(Game game) {
		List<User> activeUsers = getActiveUsers(game);

		if (activeUsers.isEmpty()) {
			return;
		}

		int index = random.nextInt(activeUsers.size());
		User winner = activeUsers.get(index);

		// Mark user as eliminated (or winner)
		winner.setEliminated(true);
		userRepository.save(winner);

		// Update game with the winner
		game.getWinnerListByOrder().add(winner.getUserId());
		game.setWinner(game.getWinnerListByOrder().size());

		// If this was the last winner, mark game as completed
		if (game.getWinnerListByOrder().size() == 12) {
			game.setGameStatus(GameStatus.COMPLETED);
		}

		gameRepository.save(game);
	}

	@Override
	public String declareWinner(User user, Game game) {
		game.getWinnerListByOrder().add(user.getUserId());
		game.setWinner(game.getWinnerListByOrder().size());
		game.setGameStatus(GameStatus.COMPLETED);
		gameRepository.save(game);
		return "User " + user.getUserId() + " is the winner";
	}

	@Override
	public User getMonthlyWinner(Long gameId, int month) {
		Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
		List<Long> winnerListByOrder = game.getWinnerListByOrder();

		if (month < 1 || month > winnerListByOrder.size()) {
			throw new RuntimeException("Invalid month or no winner for the given month");
		}

		Long winnerId = winnerListByOrder.get(month - 1);
		return userRepository.findById(winnerId).orElseThrow(() -> new RuntimeException("Winner not found"));
	}

//	@Override
//	public User sendWinningPrice(User user, double prizeamount) {
//		// Check if the user exists
//	    if (user == null) {
//	        throw new IllegalArgumentException("User cannot be null");
//	    }
//
//	    int prizeAmount;
//		// Check if the prize amount is valid
//		if (prizeAmount <= 0) {
//	        throw new IllegalArgumentException("Invalid prize amount");
//	    }
//
//	    // Simulate adding the prize amount to the user's balance
//	    double currentBalance = user.getBalance();
//	    double newBalance = currentBalance + prizeAmount;
//	    user.setBalance(newBalance);
//
//	    // Save the updated user information in the repository
//	    userRepository.save(user);
//	}

}
