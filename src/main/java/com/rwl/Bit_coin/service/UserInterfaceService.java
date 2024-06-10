package com.rwl.Bit_coin.service;

import com.rwl.Bit_coin.dtos.GameStatsDto;
import com.rwl.Bit_coin.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterfaceService {
    ResponseEntity<?> getAllUsers(String email);

    ResponseEntity<?> addUser(SignupRequest signupRequest);

    ResponseEntity<?> findUserById(int id, String agentEmail);

    ResponseEntity<String> agentForgetPasswordSendVerificationCode(String agentEmail) throws Exception;

    ResponseEntity<String> agentForgetPasswordVerifyVerificationCode(String agentEmail, String enteredOtp) throws Exception;
}
