package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.WalletTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepo extends JpaRepository<WalletTransactions, Long> {

//    @Transactional
//    @Modifying
//    @Query(value = "select sum(transaction_amount) from wallet_transactions where game_game_id = :gameId and user_Id = :userId", nativeQuery = true)
//    Double findTransactionSumByGameGameIdAndUserId(@Param("gameId") Long gameId, @Param("userId") Long userId);

    //        List<WalletTransactions> findByGameGameIdAndUserUserId(long gameId, long UserId);
    Double findSumOfTransactionAmountByGameGameIdAndUserUserId(Long gameId, Long UserId);

}