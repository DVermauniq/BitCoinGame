package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.WinRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinRecordRepository extends JpaRepository<WinRecord,Long> {
    WinRecord findByGameGameIdAndUserUserId(Long gameId, Long userId);

    List<WinRecord> findByGame(Game game);
}

