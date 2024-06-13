package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.entity.WalletTransactions;
import com.rwl.Bit_coin.entity.WinRecord;
import com.rwl.Bit_coin.enumm.TransactionType;
import com.rwl.Bit_coin.repo.GameRepo;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.repo.WalletTransactionRepo;
import com.rwl.Bit_coin.repo.WinRecordRepository;
import com.rwl.Bit_coin.service.WalletService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class WalletServiceImpl  implements WalletService {

    @Autowired
    private WinRecordRepository winRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepo gameRepository;

    @Autowired
    WalletTransactionRepo transactionRepository;

    @Override
    public void addAmountToWallet(Long userId,Double winAmount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(userId, "user not found"));

        WalletTransactions transaction = new WalletTransactions();
        transaction.setUser(user);
        transaction.setTransactionAmount(winAmount);
        transaction.setTransactionType(TransactionType.CREDITED);
        transaction.setTransactionDate(LocalDate.now());

        double currentBalance = getUserCurrentBalance(userId);
        transaction.setTotalBalance(currentBalance + winAmount);

        transactionRepository.save(transaction);
    }

    private double getUserCurrentBalance(Long userId) {
        WalletTransactions recentTransaction = transactionRepository.findByRecentTransactionDate(userId);
        return recentTransaction!= null? recentTransaction.getTotalBalance() : 0.0;
    }
}



