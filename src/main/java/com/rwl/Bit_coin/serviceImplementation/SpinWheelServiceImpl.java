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

		// Stop duplicate entries
		if (!entries.contains(user)) {
			entries.add(user);
			game.setUser(entries);
			userRepository.save(user);
			gameRepository.save(game);
		}
		return game;
	}

	@Override
	public String spinWheel(Long gameId) {
		Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
		List<User> activeUsers = getActiveUsers(game);

		if (activeUsers.size() == 1) {
			return declareWinner(activeUsers.get(0), game);
		}

		int index = random.nextInt(activeUsers.size());
		User eliminatedUser = activeUsers.get(index);

		scheduler.schedule(() -> {
			eliminatedUser.setEliminated(true);
			userRepository.save(eliminatedUser);
		}, 5, TimeUnit.SECONDS);

		return "User " + eliminatedUser.getUserId() + " has been eliminated";
	}

	@Override
	public List<User> getActiveUsers(Game game) {
		return game.getUser().stream().filter(user -> !user.isEliminated()).collect(Collectors.toList());
	}

	private String declareWinner(User winner, Game game) {
		game.setWinner(0);
		gameRepository.save(game);
		return "User " + winner.getUserId() + " is the winner";
	}

	@Override
	public String getWinner(Long gameId) {
		Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
		User winner = game.getUser().stream().filter(User::isWinner).findFirst().orElse(null);
		return (winner != null) ? "User " + winner.getUserId() + " is the winner" : "No winner yet";
	}

	@Override
	public Game getGameById(Long gameId) {
		return gameRepository.findById(gameId).orElse(null);
	}
}
