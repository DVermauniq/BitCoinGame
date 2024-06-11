package com.rwl.Bit_coin.serviceImplementation;

import com.rwl.Bit_coin.config.TwilioConfig;
import com.rwl.Bit_coin.exception.OtpNotSendException;
import com.rwl.Bit_coin.service.OtpServiceInterface;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpServiceImplementation implements OtpServiceInterface {

    // Expiry time for OTP in milliseconds
    private static final long OTP_EXPIRY_TIME_MILLIS = 2 * 60 * 1000;
    public static String otp;
    public static String currEmail;
    @Autowired
    private TwilioConfig twilioConfig;
    @Autowired
    private JavaMailSender javaMailSender;
    private long otpGenerationTimeMillis; // Timestamp for OTP generation


    @Override
    public ResponseEntity<String> sendEmailOtp(String userEmailId, String subject, String messageToSend)
            throws Exception {
        otp = generateOTP();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nitin01kumar14@gmail.com");
        message.setTo(userEmailId);
        message.setSubject(subject);
        message.setText(messageToSend + otp);
        javaMailSender.send(message);
        return ResponseEntity.ok("Message sent to " + userEmailId);
    }


    @Override
    public ResponseEntity<String> verifyEmailOtp(String userEmailId, String enteredOTP) throws Exception {
        if (otp.equals(enteredOTP)) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userEmailId);
            message.setText("your email verification is done");
            javaMailSender.send(message);
            return ResponseEntity.ok("Email " + userEmailId + " verified successfully!");
        } else {
            // Incorrect OTP
            return ResponseEntity.ok("Incorrect OTP. Email verification failed.");
        }
    }


    @Override
    public ResponseEntity<String> sendPhoneOtp(String phoneNumber) throws Exception {
        try {
            // Initialize Twilio
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
            // Adding country code +91 to the phone number
            phoneNumber = "+91" + phoneNumber;
            // Create a Twilio phone number object for recipient and sender
            PhoneNumber to = new PhoneNumber(phoneNumber);
            PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber());
            System.out.println(to);
            // Generate OTP
            otp = generateOTP();
            otpGenerationTimeMillis = System.currentTimeMillis();
            // OTP message
            String otpMessage = "Dear Customer, your OTP is " + otp
                    + " for sending SMS through Nidhi Bank application. Thank you.";
            // Send SMS using Twilio
            Message.creator(to, from, otpMessage).create();
//			return ResponseEntity.ok("OTP sent successfully.");
//		}
            // Check if OTP was sent successfully
            if (otp == null || otp.isEmpty()) {
                throw new OtpNotSendException("Failed to send OTP."); // Throw OtpNotSendException
            }
            return ResponseEntity.ok("OTP sent successfully." + otp);
        } catch (OtpNotSendException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to send OTP.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to send OTP.");
        }
    }

    @Override
    public ResponseEntity<String> verifyPhoneOtp(String phoneNumber, String enteredOtp) throws Exception {
        try {
            // Adding country code +91 to the phone number
            phoneNumber = "+91" + phoneNumber;
            // Check if OTP has expired
            long currentTimeMillis = System.currentTimeMillis();
            if ((currentTimeMillis - otpGenerationTimeMillis) > OTP_EXPIRY_TIME_MILLIS) {
                throw new com.rwl.Bit_coin.exception.OTPExpireException("OTP has been expired!"); // Throw
                // OTPExpireException
            }
            // Compare the OTP entered by the user with the OTP sent to the phone number
            if (otp != null && otp.equals(enteredOtp)) {
                // OTP is valid
                return ResponseEntity.ok("OTP is valid!");
            } else {
                // OTP is invalid
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Entered OTP is invalid!");
            }
        } catch (com.rwl.Bit_coin.exception.OTPExpireException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("OTP has been expired!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to verify OTP.");
        }
    }

    public String generateOTP() {
        // Generate a 6-digit OTP
        return String.format("%06d", new Random().nextInt(999999));
    }
}
