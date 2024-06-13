package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.WalletTransactions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepo extends JpaRepository<WalletTransactions, Long> {

//    @Transactional
//    @Modifying
//    @Query(value = "select sum(transaction_amount) from wallet_transactions where game_game_id = :gameId and user_Id = :userId", nativeQuery = true)
//    Double findTransactionSumByGameGameIdAndUserId(@Param("gameId") Long gameId, @Param("userId") Long userId);

    //        List<WalletTransactions> findByGameGameIdAndUserUserId(long gameId, long UserId);
    Double findSumOfTransactionAmountByGameGameIdAndUserUserId(Long gameId, Long UserId);
    @Modifying
    @Transactional
    @Query(value = "select * from wallet_transactions where user_id = :id order by transaction_date desc limit 1 ",nativeQuery = true)
    WalletTransactions findByUserIdAndTransactionDate(@Param("id") Long userId);
}
