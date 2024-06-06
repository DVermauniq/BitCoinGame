package com.rwl.bit_coin.controller;

import com.rwl.bit_coin.Security.payload.request.SignupRequest;
import com.rwl.bit_coin.dto.UserForgetPassword;
import com.rwl.bit_coin.serviceImpl.UserServiceImplInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImplInterface userServiceImplInterface;


    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(@RequestParam("agentEmail") String agentEmail) {
        return new ResponseEntity<>(userServiceImplInterface.getAllUsers(agentEmail), HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody SignupRequest signupRequest) throws Exception {
        return userServiceImplInterface.addUser(signupRequest);
    }

    @GetMapping("findUserById")
    public ResponseEntity<?> findUserById(@RequestParam("id") int id, @RequestParam("agentEmail") String agentEmail) throws Exception {
        return userServiceImplInterface.findUserById(id, agentEmail);
    }

    @PostMapping("/forget/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestParam("agentEmail") String agentEmail) throws Exception {
        return userServiceImplInterface.userForgetPasswordSendVerificationCode(agentEmail);
    }

    @PostMapping("/forget/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam("agentEmail") String agentEmail, @RequestParam("enteredOtp") String enteredOtp) throws Exception {
        return userServiceImplInterface.userForgetPasswordVerifyVerificationCode(agentEmail, enteredOtp);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/updateAgentPassword")
    public ResponseEntity<?> updateUserPassword(
            @RequestBody UserForgetPassword userForgetPassword
    ) {
        try {
            return userServiceImplInterface.updateUserPassword(userForgetPassword);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
