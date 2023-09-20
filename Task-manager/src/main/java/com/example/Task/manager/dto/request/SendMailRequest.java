package com.example.Task.manager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SendMailRequest {
    private final Sender sender = new Sender("IkBaba", "noreply@ik.com");
    private List<Recipient> to = new ArrayList<>();
    private String subject;
    private String htmlContent;
}
