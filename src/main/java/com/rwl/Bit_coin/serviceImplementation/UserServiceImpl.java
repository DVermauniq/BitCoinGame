package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.payload.request.SignupRequest;
import com.rwl.Bit_coin.service.UserInterfaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserInterfaceService {
    @Override
    public ResponseEntity<?> getAllUsers(String email) {
        return null;
    }

    @Override
    public ResponseEntity<?> addUser(SignupRequest signupRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> findUserById(int id, String agentEmail) {
        return null;
    }

    @Override
    public ResponseEntity<String> agentForgetPasswordSendVerificationCode(String agentEmail) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<String> agentForgetPasswordVerifyVerificationCode(String agentEmail, String enteredOtp) throws Exception {
        return null;
    }
}
