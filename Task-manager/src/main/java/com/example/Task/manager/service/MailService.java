package com.example.Task.manager.service;

import com.example.Task.manager.dto.request.SendMailRequest;

public interface MailService {
    String sendMail(SendMailRequest sendMailRequest);
}
