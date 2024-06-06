package com.rwl.Bit_coin.service;


import com.rwl.Bit_coin.dto.UserForgetPassword;
import com.rwl.Bit_coin.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserServiceInterface {

    ResponseEntity<?> addUser(SignupRequest signupRequest);

    ResponseEntity<?> getAllUsers(String userEmail);

    ResponseEntity<?> findUserById(Long id) throws Exception;

    ResponseEntity<String> userForgetPasswordSendVerificationCode(String userEmail) throws Exception;

    ResponseEntity<String> userForgetPasswordVerifyVerificationCode(String userEmail, String enteredOtp) throws Exception;

    ResponseEntity<?> updateUserPassword(UserForgetPassword userForgetPassword) throws Exception;
}
