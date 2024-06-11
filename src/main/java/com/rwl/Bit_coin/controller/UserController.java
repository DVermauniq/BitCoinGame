package com.rwl.Bit_coin.controller;

import com.rwl.Bit_coin.dtos.AddUserDto;
import com.rwl.Bit_coin.dtos.UserResponseDto;
import com.rwl.Bit_coin.payload.request.SignupRequest;
import com.rwl.Bit_coin.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;


    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody SignupRequest signupRequest) throws Exception {
        return userServiceImpl.addUser(signupRequest);
    }

    @GetMapping("findUserById")
    public ResponseEntity<?> findUserById(@RequestParam("userId") Long userId) throws Exception {
        return userServiceImpl.findUserById(userId);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(@RequestParam("agentEmail") String agentEmail) {
        return new ResponseEntity<>(userServiceImpl.getAllUsers(agentEmail), HttpStatus.OK);
    }

    @PutMapping("updatePhoneNumber")
    public ResponseEntity<?> updateUserPhoneNum(@RequestParam("userEmail") String email, @RequestParam("phoneNum") String phoneNum) {
        UserResponseDto user = userServiceImpl.updateUserPhoneNum(email, phoneNum);
        if (user == null) {
            return new ResponseEntity<>("Error occurred", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUserPassword")
    public AddUserDto updateUserPassword(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        return userServiceImpl.updateUserPassword(email, password);
    }

    @PostMapping("/forget/verifyEmail")
    public ResponseEntity<String> verifyUserEmail(@RequestParam("email") String email) throws Exception {
        return userServiceImpl.userForgetPasswordSendVerificationCode(email);
    }

    @PostMapping("/forget/verifyOtp")
    public ResponseEntity<String> verifyUserOtp(@RequestParam("email") String email, @RequestParam("enteredOtp") String enteredOtp) throws Exception {
        return userServiceImpl.userForgetPasswordVerifyVerificationCode(email, enteredOtp);
    }
}
