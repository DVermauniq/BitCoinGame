package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.dtos.GameStatsDto;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.LeaderBoardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<GameStatsDto> getTop10Winner() throws Exception {
        List<User> top10Users = userRepository.findTop10UsersByMonthlyWinning();
        return top10Users.stream()
                .map(user -> new GameStatsDto(
                        user.getFirstName(),
                        user.getLastName(),
                        0, // totalGames is not available in User entity, so set to 0
                        null, // clubType is not available in User entity, so set to null
                        user.getMonthlyWinning(),
                        user.getMonthlyWinning() // totalWinnings is not available in User entity, so set to monthlyWinning
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> getWinnerDetails(int userId) throws Exception {
        User user = userRepository.findById((long) userId).orElseThrow();
        GameStatsDto gameStatsDto = new GameStatsDto(
                user.getFirstName(),
                user.getLastName(),
                0, // totalGames is not available in User entity, so set to 0
                null, // clubType is not available in User entity, so set to null
                user.getMonthlyWinning(),
                user.getMonthlyWinning() // totalWinnings is not available in User entity, so set to monthlyWinning
        );
        return ResponseEntity.ok(gameStatsDto);
    }
}
