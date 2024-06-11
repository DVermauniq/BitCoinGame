package com.rwl.Bit_coin.service;

import com.rwl.Bit_coin.dtos.GameStatsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LeaderBoardServiceInterface {

    List<GameStatsDto> getTop10Winner() throws Exception;

    ResponseEntity<?> getWinnerDetails(int userId) throws Exception;
}
