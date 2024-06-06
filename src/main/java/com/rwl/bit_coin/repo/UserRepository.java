package com.rwl.bit_coin.repo;

import com.rwl.bit_coin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserEmail(String userEmail);

    User findByUserEmail(String userEmail);

    boolean existsByUserName(String userEmail);

    boolean existsByPhoneNumber(String userEmail);
}
