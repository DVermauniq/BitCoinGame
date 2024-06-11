package com.rwl.Bit_coin.service;

import com.rwl.Bit_coin.dtos.GameStatsDto;
import com.rwl.Bit_coin.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LeaderBoardServiceInterface {
    List<User> getTop10Winner() throws Exception;

    ResponseEntity<?> getWinnerDetails(Long userId) throws Exception;

}
