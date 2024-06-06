package com.rwl.bit_coin.service;

import com.rwl.bit_coin.Security.payload.request.SignupRequest;
import com.rwl.bit_coin.dto.UserForgetPassword;

import org.springframework.http.ResponseEntity;

public interface UserServiceInterface {
    ResponseEntity<?> getAllUsers(String userEmail);

    ResponseEntity<?> addUser(SignupRequest signupRequest);

    ResponseEntity<?> findUserById(Long id, String agentEmail);

    ResponseEntity<String> userForgetPasswordSendVerificationCode(String userEmail) throws Exception;

    ResponseEntity<String> userForgetPasswordVerifyVerificationCode(String userEmail, String enteredOtp) throws Exception;

    ResponseEntity<?> updateUserPassword(UserForgetPassword userForgetPassword) throws Exception;
}
