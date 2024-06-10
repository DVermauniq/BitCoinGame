package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT * FROM user ORDER BY monthly_winning DESC LIMIT 10")
//    @Modifying
//    @Transactional
//    List<User> findTop10UsersByMonthlyWinning();
   User findByFirstName(String firstName);

}
