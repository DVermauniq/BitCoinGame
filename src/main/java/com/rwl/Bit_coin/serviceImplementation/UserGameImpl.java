package com.rwl.Bit_coin.serviceImplementation;


import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.entity.WalletTransactions;
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
        User user = userRepo.findById(userId).orElseThrow();
        entries.add(user);
        game.setUser(entries);
        List<Game> userGame = user.getGameList();
        userGame.add(game);
        user.setGameList(userGame);
        userRepo.save(user);
        return gameRepo.save(game);
    }

    @Override
    public void startGame(Long gameId, int amount, String password) {
        Game game =gameRepo.findById(gameId).orElseThrow();
        if(game.getNumberOfPlayers()!=0){
            WalletTransactions wallet = new WalletTransactions();
            
            List<WalletTransactions> walletTransactions = game.getWalletTransactionsList();

        }
    }


}
