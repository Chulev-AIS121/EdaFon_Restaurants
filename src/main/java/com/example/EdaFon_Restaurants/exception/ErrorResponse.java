package com.example.EdaFon_Restaurants.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private UUID id;
    private String code;
    private String message;
    private LocalDateTime timestamp;
}
