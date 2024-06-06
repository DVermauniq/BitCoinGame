package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.service.SpinWheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spinwheel")
public class SpinWheelController {

    @Autowired
    private SpinWheelService spinWheelService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = spinWheelService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/spin/{gameId}")
    public ResponseEntity<User> spinWheel(@PathVariable Long gameId) {
        User eliminatedUser = spinWheelService.spinWheel(gameId);
        return ResponseEntity.ok(eliminatedUser);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestParam String name) {
        User newUser = spinWheelService.addUser(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
