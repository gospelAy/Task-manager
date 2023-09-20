package com.example.Task.manager.service.Impl;

import com.example.Task.manager.dto.request.Recipient;
import com.example.Task.manager.dto.request.SendMailRequest;
import com.example.Task.manager.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MailServiceImplTest {
    @Autowired MailService mailService;

    @Test
    void sendMail() {
        SendMailRequest sendMailRequest = new SendMailRequest();
        String name = "IkBossman";
        sendMailRequest.setHtmlContent("<h1> Hello, This is just a testing. </h1>");
        sendMailRequest.setSubject("Testing");
        Recipient recipient = new Recipient(name, "gosple531@gmail.com");
        sendMailRequest.getTo().add(recipient);

        String response = mailService.sendMail(sendMailRequest);
        assertThat(response).isNotNull();
    }
}