package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.repo.GameRepo;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.UserGameInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGameImpl implements UserGameInterface {

    @Autowired
    GameRepo gameRepo;
    @Autowired
    UserRepository userRepo;


    @Override
    public Game enterInGame(Long userId, Long gameId) {
        Game game = gameRepo.findById(gameId).orElseThrow();
        List<User> entries = game.getUser();
        entries.add(userRepo.findById(userId).orElseThrow());
        game.setUser(entries);
        User user = userRepo.findById(userId).orElseThrow();
        List<Game> usergame = user.getGameList();
        usergame.add(game);
        user.setGameList(usergame);
        userRepo.save(user);
        return gameRepo.save(game);
    }
}
