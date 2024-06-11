package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.enumm.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "select * from user order by monthly_winning desc limit 10", nativeQuery = true)
    List<User> findTop10UsersByMonthlyWinning();

    User findByFirstName(String firstName);

    //    @Query("SELECT * FROM users ORDER BY monthlyWinning DESC LIMIT 10;")
//    List<User> findTop10UsersByMonthlyWinning();
    boolean existsByEmail(String email);

    User findByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<User> findUserByRating(Rating rating);
}
