package com.rwl.bit_coin.Security.repository;

import com.rwl.bit_coin.Security.models.ERole;
import com.rwl.bit_coin.Security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}

