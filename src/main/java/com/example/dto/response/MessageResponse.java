package com.example.dto.response;

public class MessageResponse {
    private final String result;

    public MessageResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}