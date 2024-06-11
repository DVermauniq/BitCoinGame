package com.rwl.Bit_coin.repo;

import com.rwl.Bit_coin.entity.WinRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinRecordRepository extends JpaRepository<WinRecord,Long> {
    WinRecord findByGameIdAndUserId(Long gameId, Long userId);
}
