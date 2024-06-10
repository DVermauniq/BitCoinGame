package com.rwl.Bit_coin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rwl.Bit_coin.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
