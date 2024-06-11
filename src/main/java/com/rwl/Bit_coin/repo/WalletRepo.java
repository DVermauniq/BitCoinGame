package com.rwl.Bit_coin.repo;


import com.rwl.Bit_coin.entity.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Long> {
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM wallet WHERE wallet.user_id = :id",nativeQuery = true)
    Optional<Wallet> findByUserId(@Param("id") Long userId);
}
