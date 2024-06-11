package com.rwl.Bit_coin.game;

import com.rwl.Bit_coin.dtos.WinRecordDto;
import com.rwl.Bit_coin.entity.Game;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.entity.WinRecord;
import com.rwl.Bit_coin.repo.GameRepo;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.repo.WinRecordRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GameServiceImpl implements GameServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepo gameRepository;
    @Autowired
    private WinRecordRepository winRecordRepository;

    @Override
    public WinRecord setWinnerUser(Long gameId, Long userId, Double winAmount) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new ObjectNotFoundException(gameId, "game not found."));
        User user = userRepository.findById(userId).orElseThrow();

        WinRecord winRecord = new WinRecord();
        winRecord.setGame(game);
        winRecord.setUser(user);
        winRecord.setWinAmount(winAmount);
        winRecord.setDate(LocalDate.now());
        return winRecordRepository.save(winRecord);
    }

    @Override
    public WinRecordDto getWinRecord(Long gameId, Long userId) throws ObjectNotFoundException {
        WinRecord winRecord = winRecordRepository.findByGameGameIdAndUserUserId(gameId, userId);

        WinRecordDto winRecordDto = new WinRecordDto();
        winRecordDto.setRecordId(winRecord.getRecordId());
        winRecordDto.setFirstName(winRecord.getUser().getFirstName());
        winRecordDto.setLastName(winRecord.getUser().getLastName());
        winRecordDto.setWinAmount(winRecord.getWinAmount());
        winRecordDto.setDate(winRecord.getDate());

        return winRecordDto;
    }


}
