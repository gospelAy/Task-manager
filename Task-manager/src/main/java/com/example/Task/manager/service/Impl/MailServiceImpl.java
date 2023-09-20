package com.example.Task.manager.service.Impl;

import com.example.Task.manager.config.MailConfig;
import com.example.Task.manager.dto.request.SendMailRequest;
import com.example.Task.manager.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private final MailConfig mailConfig;
    @Override
    public String sendMail(SendMailRequest sendMailRequest) {
//        try{
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("api-key", mailConfig.getMailApiKey());
        httpHeaders.set("accept", APPLICATION_JSON_VALUE);

        RequestEntity<SendMailRequest> entity =
                new RequestEntity<>(sendMailRequest, httpHeaders, HttpMethod.POST, URI.create(mailConfig.getMailUrl()));
        ResponseEntity<String> response = restTemplate.postForEntity(mailConfig.getMailUrl(), entity, String.class);
        log.info("::::::::::::::::::::::::::EMAIL SENT SUCCESSFULLY:::::::::::::::::::::::::::::::");
        return response.getBody();
//        }catch (Exception exception){
//            throw new RuntimeException("Error sending mail");
//        }
    }
}
