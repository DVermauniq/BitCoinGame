package com.rwl.Bit_coin.serviceImpl;


import com.rwl.Bit_coin.dto.UserForgetPassword;
import com.rwl.Bit_coin.dto.UserResponseDto;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.payload.request.SignupRequest;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.UserServiceInterface;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    OtpServiceImplementation otpServiceImplementation;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepo;



    @Override
    public ResponseEntity<?> addUser(@NotNull SignupRequest signupRequest) {

        if (userRepo.existsByUserEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("Email already taken", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepo.existsByPhoneNumber(signupRequest.getPhoneNumber())) {
            return new ResponseEntity<>("Phone number already taken", HttpStatus.NOT_ACCEPTABLE);
        }
        //creation of new user
        try {
            User newUser = new User();
            newUser.setFirstName(signupRequest.getFirstName());
            newUser.setLastName(signupRequest.getLastName());
            newUser.setUserEmail(signupRequest.getEmail());
            newUser.setPhoneNumber(signupRequest.getPhoneNumber());
            newUser.setPassword(encoder.encode(signupRequest.getPassword()));
            userRepo.save(newUser);
        } catch (Exception e) {
            return new ResponseEntity<>("Email Error" + e.getMessage(), HttpStatus.OK);
        }

//        // Set default role as USER for user
//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
//        if (userRole == null) {
//            return new ResponseEntity<>("User role not found", HttpStatus.NOT_FOUND);
//        }
////                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//        newUser.setRoles(roles);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> findUserById(Long userId) throws Exception {
        User user = userRepo.findById(userId).orElseThrow(() -> {
            return new Exception("User not found with id " + userId);
        });
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName(user.getFirstName());
        userResponseDto.setUserName(user.getLastName());
        userResponseDto.setEmail(user.getUserEmail());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setDob(user.getDob());
        userResponseDto.setAadharNo(user.getAadharNo());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<?> getAllUsers(String userEmail) {
        List<User> users = (List<User>) userRepo.findByUserEmail(userEmail);
        if (users.size() == 0) return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setUserName(user.getFirstName());
            userResponseDto.setEmail(user.getUserEmail());
            userResponseDto.setPhoneNumber(user.getPhoneNumber());
            userResponseDtoList.add(userResponseDto);
        }
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> userForgetPasswordSendVerificationCode(String userEmail) throws Exception {
        //check if user already exists
        if (!userRepo.existsByUserEmail(userEmail)) {
            throw new Exception("This email is not registered with us");
        }
//        if (!userRepo.existsByPhoneNumber( phoneNumber)) {
//            throw new Exception("This phone number is not registered with us");
//        }
        try {
            String otp = otpServiceImplementation.generateOTP();
            String subject = "Forgot password attempted";
            String messageToSend = "Your verification OTP is: ";
            otpServiceImplementation.sendEmailOtp(userEmail,subject, messageToSend, otp);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity("OTP send", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> userForgetPasswordVerifyVerificationCode(String userEmail, String enteredOtp) throws Exception {
        try {
            otpServiceImplementation.verifyEmailOtp(userEmail, enteredOtp);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity("Email Verify Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUserPassword(UserForgetPassword userForgetPassword) throws Exception {
        User currUser = userRepo.findByUserEmail(userForgetPassword.getEmail());

        UserResponseDto userDto = new UserResponseDto();

        currUser.setPassword(encoder.encode(userForgetPassword.getPassword()));
        try {
            if (currUser !=null) {
                currUser.setPassword(encoder.encode(userForgetPassword.getPassword()));
                userRepo.save(currUser);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

//            userDto.setUserId(currUser.getUserId());
            userDto.setUserName(currUser.getFirstName());
            userDto.setEmail(currUser.getUserEmail());
            userDto.setPhoneNumber(currUser.getPhoneNumber());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
