package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Component
public class MessageSender implements Function<UserRequest, String> {

    @Value("${myapp.url}")
    private String webhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String apply(UserRequest request) {
        String message = "{\"text\": \"this is the test message.\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(webhookUrl, HttpMethod.POST, entity, String.class);

        return response.getStatusCode().is2xxSuccessful() ? "Message sent successfully!" : "Failed to send message.";
    }
}
