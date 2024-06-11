package com.rwl.Bit_coin.serviceImplementation;


import com.rwl.Bit_coin.dtos.AddUserDto;
import com.rwl.Bit_coin.dtos.UserResponseDto;
import com.rwl.Bit_coin.entity.User;
import com.rwl.Bit_coin.payload.request.SignupRequest;
import com.rwl.Bit_coin.repo.UserRepository;
import com.rwl.Bit_coin.service.UserInterfaceService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserInterfaceService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpServiceImplementation otpServiceImplementation;


    @Override
    public ResponseEntity<?> addUser(@NotNull SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("Email already taken", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepository.existsByPhoneNumber(signupRequest.getPhoneNumber())) {
            return new ResponseEntity<>("Phone number already taken", HttpStatus.NOT_ACCEPTABLE);
        }
        //creation of new user
        try {
            User newUser = new User();
            newUser.setFirstName(signupRequest.getFirstName());
            newUser.setLastName(signupRequest.getLastName());
            newUser.setEmail(signupRequest.getEmail());
            newUser.setPhoneNumber(signupRequest.getPhoneNumber());
//            newUser.setPassword(encoder.encode(signupRequest.getPassword()));
            userRepository.save(newUser);
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
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> findUserById(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            return new Exception("User not found with id " + userId);
        });
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setDob(user.getDob());
        userResponseDto.setAadharNo(user.getAadhaarNo());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> getAllUsers(String userEmail) {
        List<User> users = (List<User>) userRepository.findByEmail(userEmail);
        if (users.size() == 0) return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setFirstName(user.getFirstName());
            userResponseDto.setLastName(user.getLastName());
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setPhoneNumber(user.getPhoneNumber());
            userResponseDtoList.add(userResponseDto);
        }
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }


    @Override
    public UserResponseDto updateUserPhoneNum(String email, String phoneNum) {
        User user = userRepository.findByEmail(email);
        if (user == null || user.getPhoneNumber().equals(phoneNum) || userRepository.existsByPhoneNumber(phoneNum)) {
            return null;
        }
        user.setPhoneNumber(phoneNum);
        userRepository.save(user);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        return userResponseDto;
    }

    @Override
    public AddUserDto updateUserPassword(String email, String password) throws Exception {
        User currUser = userRepository.findByEmail(email);
        AddUserDto newUser = new AddUserDto();
//        currUser.setPassword(encoder.encode(password));
        try {
            userRepository.save(currUser);
            newUser.setFirstName(currUser.getFirstName());
            newUser.setLastName(currUser.getLastName());
            newUser.setEmail(currUser.getEmail());
            newUser.setPhoneNumber(currUser.getPhoneNumber());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return newUser;
    }

    @Override
    public ResponseEntity<String> userForgetPasswordSendVerificationCode(String email) throws Exception {
        //check if user already exists
        if (!userRepository.existsByEmail(email)) {
            throw new Exception("This email is not registered with us");
        }
        try {
            String otp = otpServiceImplementation.generateOTP();
            String subject = "Forgot password attempted";
            String messageToSend = "Your verification OTP is: ";
            otpServiceImplementation.sendEmailOtp(email, subject, messageToSend);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity("OTP send", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> userForgetPasswordVerifyVerificationCode(String email, String enteredOtp) throws Exception {
        try {
            otpServiceImplementation.verifyEmailOtp(email, enteredOtp);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity("Email Verify Successfully", HttpStatus.OK);
    }


//    @Override
//    public ResponseEntity<?> updateUser(UpdateUserDto updateUserDto){
//        User user = userRepository.findByEmail(updateUserDto.getEmail());
//        if(user==null)return new ResponseEntity("User not found",HttpStatus.NOT_FOUND);
//        KycDetails kycDetails = user.getKycDetails();
//        if(kycDetails==null) return  new ResponseEntity("No Kyc details found",HttpStatus.NOT_FOUND);
//        kycDetails.setFirstName(updateUserDto.getFirstName());
//        kycDetails.setLastName(updateUserDto.getLastName());
//        kycDetails.setPhnNo(updateUserDto.getAlternatePhnNo());
//        kycDetails.setMartialStatus(updateUserDto.getMartialStatus());
//
//        Address address = kycDetails.getAddress();
//        address.setAddress(updateUserDto.getPermanentAddress().getAddress());
//        address.setDistrict(updateUserDto.getPermanentAddress().getDistrict());
//        address.setState(updateUserDto.getPermanentAddress().getState());
////        address.setKycDetails(kycDetails);
//        addressRepo.save(address);
//
//        kycDetails.setEducation(updateUserDto.getEducation());
//
//        kycDetailsRepo.save(kycDetails);
//        userRepository.save(user);
//        return new ResponseEntity<>("User updated",HttpStatus.OK);
//    }
}
