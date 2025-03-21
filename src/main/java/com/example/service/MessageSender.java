package com.example.service;

import com.example.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

@Component
public class MessageSender implements Function<UserRequest, String> {

    @Value("${myapp.url}")
    private String webhookUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public MessageSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String apply(UserRequest request) {
        String message = "{\"text\": \"12시입니다~! 다들 명찰 챙기기 잊지 마세요:smile: \"}";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(webhookUrl, HttpMethod.POST, entity, String.class);

        return response.getStatusCode().is2xxSuccessful() ? "Message sent successfully!" : "Failed to send message.";
    }
}