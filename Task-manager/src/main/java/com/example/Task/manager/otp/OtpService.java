package com.example.Task.manager.otp;

import com.example.Task.manager.model.User;

public interface OtpService {
    String generateAndSaveOtp(User user);
    OtpEntity validateOtp(String otp);
    void deleteOtp(OtpEntity otpEntity);
}
