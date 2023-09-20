package com.example.Task.manager.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MailConfig {
    private String mailApiKey;
    private String mailUrl;
}
