package com.rwl.Bit_coin.game;

import com.rwl.Bit_coin.dtos.WinRecordDto;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.entity.WinRecord;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/winner-game")
public class GameController {
    @Autowired
    private GameServiceImpl gameService;

    @PostMapping("/setWinner")
    public ResponseEntity<WinRecord> setWinnerUser(@RequestParam Long gameId, @RequestParam Long userId, @RequestParam Double winAmount) {
        WinRecord winRecord = gameService.setWinnerUser(gameId, userId, winAmount);
        return new ResponseEntity<>(winRecord, HttpStatus.OK);
    }

    @GetMapping("/getWinRecord")
    public ResponseEntity<WinRecordDto> getWinRecord(@RequestParam Long gameId, @RequestParam Long userId) throws ObjectNotFoundException {
        WinRecordDto winRecordDto = gameService.getWinRecord(gameId, userId);
        return new ResponseEntity<>(winRecordDto, HttpStatus.OK);
    }
}
