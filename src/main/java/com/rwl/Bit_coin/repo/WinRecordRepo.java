package com.rwl.Bit_coin.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.WinRecord;

public interface WinRecordRepo extends JpaRepository<WinRecord, Integer> {

	List<WinRecord> findByGameAndDateBetween(Game game, LocalDate atDay, LocalDate atEndOfMonth);

}
