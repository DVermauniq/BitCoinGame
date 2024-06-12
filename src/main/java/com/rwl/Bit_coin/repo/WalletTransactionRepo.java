package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.WalletTransactions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepo extends JpaRepository<WalletTransactions,Long> {
    @Modifying
    @Transactional
    @Query(value = "select * from wallet_transactions order by transaction_date desc limit 1",nativeQuery = true)
    WalletTransactions findByRecentTransactionDate();
}
