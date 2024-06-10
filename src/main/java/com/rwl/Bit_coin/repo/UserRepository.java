package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT * FROM user ORDER BY monthlyWinning DESC LIMIT 10;")
    List<User> findTop10UsersByMonthlyWinning();
   User findByUsername(String firstName);
   User findByFirstname(String firstName);

//    @Query("SELECT * FROM users ORDER BY monthlyWinning DESC LIMIT 10;")
//    List<User> findTop10UsersByMonthlyWinning();
//   User findByUsername(String firstName);
//   User findByFirstname(String firstName);

}
