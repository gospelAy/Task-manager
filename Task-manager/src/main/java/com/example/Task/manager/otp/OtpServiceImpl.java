package com.example.Task.manager.otp;

import com.example.Task.manager.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService{
    private final OtpRepository otpRepository;
    @Override
    public String generateAndSaveOtp(User user) {
        OtpEntity existingOtp = otpRepository.findByUser(user);
        if(existingOtp != null)
            otpRepository.delete(existingOtp);
        OtpEntity otpEntity = new OtpEntity();
        String otp = generateOtp();
        otpEntity.setOtp(otp);
        otpEntity.setUser(user);
        otpRepository.save(otpEntity);
        return otp;
    }

    private static String generateOtp() {
        final SecureRandom secureRandom = new SecureRandom();
        return String.valueOf(secureRandom.nextInt(10000, 100000));
    }

    @Override
    public OtpEntity validateOtp(String otp) {
        OtpEntity otpEntity = otpRepository.findByOtp(otp);
        if(otpEntity == null)
            throw new RuntimeException("Otp is invalid");
        else if(otpEntity.getExpiryTime().isAfter(LocalDateTime.now())){
            otpRepository.delete(otpEntity);
            throw new RuntimeException("Otp is expired");
        }
        return otpEntity;
    }

    @Override
    public void deleteOtp(OtpEntity otpEntity) {
        otpRepository.delete(otpEntity);
    }
}
