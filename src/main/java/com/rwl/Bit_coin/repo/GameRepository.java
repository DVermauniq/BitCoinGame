package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
