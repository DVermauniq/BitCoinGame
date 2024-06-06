package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT w FROM User w ORDER BY w.totalPrice DESC")
    List<User> findTop10UsersByMonthlyWinning();
}
