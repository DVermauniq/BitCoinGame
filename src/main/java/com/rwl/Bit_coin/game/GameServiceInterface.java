package com.rwl.Bit_coin.game;

import com.rwl.Bit_coin.dtos.WinRecordDto;
import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.entity.WinRecord;

import java.util.Map;

public interface GameServiceInterface {

    WinRecord setWinnerUser(Long gameId,Long userId,Double winAmount);
    WinRecordDto getWinRecord(Long gameId, Long userId);

}