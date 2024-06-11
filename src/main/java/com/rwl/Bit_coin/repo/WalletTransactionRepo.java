package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.WalletTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepo extends JpaRepository<WalletTransactions,Long> {
}
