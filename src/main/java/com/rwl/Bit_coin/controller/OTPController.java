package com.rwl.Bit_coin.controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rwl.Bit_coin.service.OtpServiceInterface;

@RestController
@RequestMapping("/")
public class OTPController {

	@Autowired
	private OtpServiceInterface otpService;

	@PostMapping("/send-email-otp")
	public ResponseEntity<String> sendEmailOtp(@RequestParam String userEmailId, @RequestParam String subject,
			@RequestParam String message) {
		try {
			return otpService.sendEmailOtp(userEmailId, subject, message);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to send email OTP.");
		}
	}

	@PostMapping("/verify-email-otp")
	public ResponseEntity<String> verifyEmailOtp(@RequestParam String userEmailId, @RequestParam String otp) {
		try {
			return otpService.verifyEmailOtp(userEmailId, otp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to verify email OTP.");
		}
	}

	@PostMapping("/send-phone-otp")
	public ResponseEntity<String> sendPhoneOtp(@RequestParam String phoneNumber) {
		try {
			return otpService.sendPhoneOtp(phoneNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to send phone OTP.");
		}
	}

	@PostMapping("/verify-phone-otp")
	public ResponseEntity<String> verifyPhoneOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
		try {
			return otpService.verifyPhoneOtp(phoneNumber, otp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to verify phone OTP.");
		}
	}

}
