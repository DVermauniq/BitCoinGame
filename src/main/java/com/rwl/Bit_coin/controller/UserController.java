package com.rwl.Bit_coin.controller;


import com.rwl.Bit_coin.dto.UserForgetPassword;
import com.rwl.Bit_coin.payload.request.SignupRequest;
import com.rwl.Bit_coin.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;


    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(@RequestParam("agentEmail") String agentEmail) {
        return new ResponseEntity<>(userServiceImpl.getAllUsers(agentEmail), HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody SignupRequest signupRequest) throws Exception {
        return userServiceImpl.addUser(signupRequest);
    }

    @GetMapping("findUserById")
    public ResponseEntity<?> findUserById(@RequestParam("userId") Long userId) throws Exception {
        return userServiceImpl.findUserById(userId);
    }

    @PostMapping("/forget/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestParam("agentEmail") String agentEmail) throws Exception {
        return userServiceImpl.userForgetPasswordSendVerificationCode(agentEmail);
    }

    @PostMapping("/forget/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam("agentEmail") String agentEmail, @RequestParam("enteredOtp") String enteredOtp) throws Exception {
        return userServiceImpl.userForgetPasswordVerifyVerificationCode(agentEmail, enteredOtp);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/updateAgentPassword")
    public ResponseEntity<?> updateUserPassword(
            @RequestBody UserForgetPassword userForgetPassword
    ) {
        try {
            return userServiceImpl.updateUserPassword(userForgetPassword);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
