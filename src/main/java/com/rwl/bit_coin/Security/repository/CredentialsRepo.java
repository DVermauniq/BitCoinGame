package com.rwl.bit_coin.Security.repository;



import com.rwl.bit_coin.Security.models.Credentials;
import com.rwl.bit_coin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepo extends JpaRepository<Credentials, Long> {
    Optional<Credentials> findByUsername(String username);
    Credentials save(Agent agent);
    Credentials save(User user);
    Credentials save(Admin admin);

    Optional<Credentials> findByEmail(String email);

    Optional<Credentials> findByPhoneNumber(String phoneNumber);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    Optional<Credentials> findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);
}
