package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.dtos.GameStatsDto;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.game.ServiceImpl;
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

    @Autowired
    private ServiceImpl service;

    @Override
    public List<User> getTop10Winner() throws Exception {
        return userRepository.findTop10UsersByMonthlyWinning();
    }

    @Override
    public ResponseEntity<?> getWinnerDetails(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow();

        GameStatsDto gameStatsDto = new GameStatsDto(user.getFirstName(), user.getLastName(), service.findGamesByUserId(userId).get(0).size() + service.findGamesByUserId(userId).get(1).size(), service.findGamesByUserId(userId).get(1).size(), service.findGamesByUserId(userId).get(0).size(), user.getMonthlyWinning())
        return ResponseEntity.ok(gameStatsDto);
    }
}
