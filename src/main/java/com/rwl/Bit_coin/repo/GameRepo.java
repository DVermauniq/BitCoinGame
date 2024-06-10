package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepo extends JpaRepository<Game,Long> {

}
