package com.rwl.Bit_coin.serviceImplementation;


import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.entity.WalletTransactions;
import com.rwl.Bit_coin.enumm.TransactionStatus;
import com.rwl.Bit_coin.enumm.TransactionType;
import com.rwl.Bit_coin.repo.GameRepo;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.repo.WalletTransactionRepo;
import com.rwl.Bit_coin.service.UserGameInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserGameImpl implements UserGameInterface {

    @Autowired
    GameRepo gameRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    WalletTransactionRepo transaction;

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

    //to be continued for verifying password before payment
    @Override
    public WalletTransactions startGame(Long gameId, Long userId, String password) {
        Game game =gameRepo.findById(gameId).orElseThrow();
        User user =userRepo.findById(userId).orElseThrow();
        WalletTransactions transact = transaction.findByRecentTransactionDate(userId);
        double tempBalance = transact.getTotalBalance();
        transact = new WalletTransactions();
        transact.setTotalBalance(tempBalance);
        transact.setGame(game);
        transact.setUser(user);
        transact.setTransactionAmount(game.getAmountPerPerson());
        transact.setTransactionType(TransactionType.DEBITED);
        transact.setTransactionDate(LocalDate.now());
        transaction.save(transact);
        transact.setTransactionStatus(TransactionStatus.PENDING);

        if(game.getNumberOfPlayers()!=0){
            game.setTotalAmountCollected(game.getTotalAmountCollected()+transact.getTransactionAmount());
            List<WalletTransactions> walletTransactions = game.getWalletTransactionsList();
            walletTransactions.add(transact);
            game.setWalletTransactionsList(walletTransactions);
            transact.setTransactionStatus(TransactionStatus.COMPLETED);
            enterInGame(userId, gameId);
            game.setNumberOfPlayers(game.getNumberOfPlayers()-1);

        }
        else{
            transact.setTransactionStatus(TransactionStatus.FAILED);
            transact.setTotalBalance(tempBalance);
            transaction.save(transact);

        }

        return transact;
    }


}
