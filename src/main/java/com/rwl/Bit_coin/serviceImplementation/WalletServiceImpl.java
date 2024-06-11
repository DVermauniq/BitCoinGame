package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.entity.WinRecord;
import com.rwl.Bit_coin.repo.GameRepo;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.repo.WinRecordRepository;
import com.rwl.Bit_coin.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WalletServiceImpl  implements WalletService {

    @Autowired
    private WinRecordRepository winRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepo gameRepository;

    @Override
    public void addAmountToWallet(Long gameId,Double winAmount) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        List<WinRecord> winRecords = winRecordRepository.findByGame(game);

        for (WinRecord winRecord : winRecords) {
            User user = winRecord.getUser();

            // winAmount = winRecord.getWinAmount();

            // Add the win amount to the user's wallet
            user.setMonthlyWinning(user.getMonthlyWinning() + winAmount);

            // Save the updated user
            userRepository.save(user);
        }
    }


}
