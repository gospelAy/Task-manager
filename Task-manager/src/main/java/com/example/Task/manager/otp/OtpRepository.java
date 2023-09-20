package com.example.Task.manager.otp;

import com.example.Task.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    OtpEntity findByOtp(String otp);
    OtpEntity findByUser(User user);
}
