package com.rwl.bit_coin.serviceImpl;

import com.rwl.bit_coin.Security.models.Credentials;
import com.rwl.bit_coin.Security.models.ERole;
import com.rwl.bit_coin.Security.models.Role;
import com.rwl.bit_coin.Security.payload.request.SignupRequest;
import com.rwl.bit_coin.Security.repository.CredentialsRepo;
import com.rwl.bit_coin.Security.repository.RoleRepository;
import com.rwl.bit_coin.dto.UserForgetPassword;
import com.rwl.bit_coin.dto.UserResponseDto;
import com.rwl.bit_coin.entity.User;
import com.rwl.bit_coin.repo.UserRepository;
import com.rwl.bit_coin.service.UserServiceInterface;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImplInterface implements UserServiceInterface {

    @Autowired
    OtpServiceImplementation otpServiceImplementation;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CredentialsRepo credentialsRepo;
    @Autowired
    CredentialsRepo credRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepo;

    @Override
    public ResponseEntity<?> getAllUsers(String userEmail) {
//        Agent agent = agentRepo.findByAgentEmail(email);
        List<User> users = agent.getUserList();
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
    public ResponseEntity<?> addUser(@NotNull SignupRequest signupRequest) {

        if (userRepo.existsByUserEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email already taken", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepo.existsByUserName(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Username already taken", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userRepo.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return new ResponseEntity<>("Phone number already taken", HttpStatus.NOT_ACCEPTABLE);
        }

        //creation of new user
        User newUser = new User();
        newUser.setFirstName(signUpRequest.getUsername());
        newUser.setUserEmail(signUpRequest.getEmail());
        newUser.setPhoneNumber(signUpRequest.getPhoneNumber());
        try {
//            String tempPassword = "user21";
//                    otpServiceImplementation.generateOTP();
//            String subject = newUser.getUserName();
//            String messageToSend = "Welcome to Nidhi Bank,Your temporary system generated password is: ";

            String tempPassword = otpServiceImplementation.generateOTP();
            String subject = "Your temporary password";
            String messageToSend = "Your temporary system generated password is: ";
            System.out.println("Sending email");
            otpServiceImplementation.sendEmailOtp(newUser.getUserEmail(), subject, messageToSend, tempPassword);
            newUser.setPassword(encoder.encode(tempPassword));
            userRepo.save(newUser);
        } catch (Exception e) {
            return new ResponseEntity<>("Email Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Credentials credentials = new Credentials(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPhoneNumber(),
                newUser.getPassword());

        // Set default role as USER for user
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
        if (userRole == null) {
            return new ResponseEntity<>("User role not found", HttpStatus.NOT_FOUND);
        }
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        credentials.setRoles(roles);
        newUser.setRoles(roles);
        userRepo.save(newUser);
        credentialsRepo.save(credentials);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName(newUser.getFirstName());
        userResponseDto.setEmail(newUser.getUserEmail());
        userResponseDto.setPhoneNumber(newUser.getPhoneNumber());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findUserById(Long userId, String userEmail) {
//        Agent agent = agentRepo.findByAgentEmail(agentEmail);
        User user = userRepo.findById(userId).get();
//        if (!user.getAgent().getAgentEmail().equals(agentEmail)) {
//            return new ResponseEntity<>("This user is not associated with this agent", HttpStatus.NOT_FOUND);
//        }
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName(user.getFirstName());
        userResponseDto.setEmail(user.getUserEmail());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> userForgetPasswordSendVerificationCode(String userEmail) throws Exception {
        //check if agent already exists
        if (!userRepo.existsByUserEmail(userEmail)) {
            throw new Exception("This email is not registered with us");
        }
        //
        try {
            String otp = otpServiceImplementation.generateOTP();
            String subject = "Forgot password attempted";
            String messageToSend = "Your verification OTP is: ";
            otpServiceImplementation.sendEmailOtp(userEmail, subject, messageToSend, otp);
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
//        Agent currAgent = agentRepo.findByAgentEmail(userForgetPassword.getEmail());

//        AddAgentDto agentDto = new AddAgentDto();

        currAgent.setAgentPassword(encoder.encode(userForgetPassword.getPassword()));
        try {
            Optional<Credentials> currAgent1 = credRepo.findByEmail(userForgetPassword.getEmail());
            if (currAgent1.isPresent()) {
                currAgent1.get().setPassword(encoder.encode(userForgetPassword.getPassword()));

                credRepo.save(currAgent1.get());
                agentRepo.save(currAgent);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            agentDto.setAgentId(currAgent.getAgentId());
            agentDto.setAgentName(currAgent.getAgentName());
            agentDto.setAgentEmail(currAgent.getAgentEmail());
            agentDto.setAgentPhoneNum(currAgent.getAgentPhoneNum());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ResponseEntity<>(agentDto, HttpStatus.OK);
    }
}
