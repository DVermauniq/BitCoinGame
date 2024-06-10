package com.rwl.Bit_coin.serviceImplementation;

import org.springframework.http.ResponseEntity;

import com.rwl.Bit_coin.service.OtpServiceInterface;

public class OtpServiceImplementation implements OtpServiceInterface {

	@Override
	public ResponseEntity<String> sendEmailOtp(String email, String subject, String messageToSend, String otp)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> verifyEmailOtp(String email, String enteredOtp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> sendPhoneOtp(String phoneNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> verifyPhoneOtp(String phoneNumber, String enteredOtp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
