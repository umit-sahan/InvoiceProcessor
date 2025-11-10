package com.example.invoice_processor.dto;

public class ApiResponse {
    private String message;

    // Constructors
    public ApiResponse() {}

    public ApiResponse(String message) {
        this.message = message;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}