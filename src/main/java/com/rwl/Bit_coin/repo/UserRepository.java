package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//   User findByUsername(String firstName);
}
