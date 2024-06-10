package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.dtos.GameStatsDto;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.serviceImplementation.LeaderBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class LeaderBoardController {
    @Autowired
    LeaderBoardServiceImpl leaderBoardService;

    @GetMapping("/getTop10Winner")
    public List<User> getTop10Winner() throws Exception{
        return leaderBoardService.getTop10Winner();
    }

    @GetMapping("/getWinnerDetails")
    public ResponseEntity<?> getWinnerDetails(@RequestParam("userId") Long userId) throws Exception{
        return leaderBoardService.getWinnerDetails(userId);
    }
}
