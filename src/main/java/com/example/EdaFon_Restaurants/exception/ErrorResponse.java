package com.example.EdaFon_Restaurants.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private UUID errorId;
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;
}