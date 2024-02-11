package com.kp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private String path;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;

}