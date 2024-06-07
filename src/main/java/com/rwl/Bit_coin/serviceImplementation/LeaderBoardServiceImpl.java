package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.dtos.GameStatsDto;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.LeaderBoardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<GameStatsDto> getTop10Winner() throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> getWinnerDetails(int userId) throws Exception {
        return null;
    }
}
