package com.rwl.Bit_coin.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User spinWheel(Long gameId) {
		Optional<Game> optionalGame = gameRepository.findById(gameId);
		if (!optionalGame.isPresent()) {
			throw new RuntimeException("Game not found");
		}

		Game game = optionalGame.get();
		List<User> activeUsers = getActiveUsers(game);

		if (activeUsers.size() == 1) {
			return declareWinner(activeUsers.get(0), game);
		}

		int index = random.nextInt(activeUsers.size());
		User eliminatedUser = activeUsers.get(index);
		eliminatedUser.setEliminated(true);
		userRepository.save(eliminatedUser);

		return eliminatedUser;
	}

	@Override
	public User addUser(String name) {
		User newUser = new User();
		newUser.setFirstName(name);
		newUser.setEliminated(false);
		userRepository.save(newUser);
		return newUser;
	}

	private List<User> getActiveUsers(Game game) {
		return game.getUsers().stream().filter(user -> !user.isEliminated()).collect(Collectors.toList());
	}

	private User declareWinner(User winner, Game game) {
		winner.setWinner(true);
		userRepository.save(winner);

		if (game.getWinnerListByOrder() == null) {
			game.setWinnerListByOrder(new ArrayList<>());
		}
		game.getWinnerListByOrder().add(winner.getUserId());

		gameRepository.save(game);

		return winner;
	}
	
	@Override
	public User getWinner(long gameId) {
		Optional<Game> optionalGame = gameRepository.findById(gameId);
		if (!optionalGame.isPresent()) {
			throw new RuntimeException("Game not found");
		}

		Game game = optionalGame.get();
		for (User user : game.getUsers()) {
			if (user.isWinner()) {
				return user;
			}
		}
		return null;
	}


}
