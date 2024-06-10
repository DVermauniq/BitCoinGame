package com.rwl.Bit_coin.service;


import com.rwl.Bit_coin.dtos.AddUserDto;
import com.rwl.Bit_coin.dtos.UserResponseDto;
import com.rwl.Bit_coin.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;


public interface UserInterfaceService {


    ResponseEntity<?> addUser(SignupRequest signupRequest);

    ResponseEntity<?> findUserById(Long userId) throws Exception;

    ResponseEntity<?> getAllUsers(String email);

    UserResponseDto updateUserPhoneNum(String email, String phoneNum);

    AddUserDto updateUserPassword(String email, String password) throws Exception;

    ResponseEntity<String> userForgetPasswordSendVerificationCode(String email) throws Exception;

    ResponseEntity<String> userForgetPasswordVerifyVerificationCode(String email, String enteredOtp) throws Exception;

//    ResponseEntity<?> updateUser(UpdateUserDto updateUserDto);

}
