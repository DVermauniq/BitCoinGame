package com.rwl.Bit_coin.game;

import com.rwl.Bit_coin.dtos.UserGameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class Controller {
    @Autowired
    private ServiceInterface serviceInterface;
    @GetMapping("userId")
    public List<List<UserGameDto>> findGamesByUserId(@RequestParam Long userId) {
        return serviceInterface.findGamesByUserId(userId);
    }
}
