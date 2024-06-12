package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.enumm.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game,Long> {

}
