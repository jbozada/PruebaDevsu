package com.devsu.hackerearth.backend.account.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDto {

    private int code;
    private String error;
    private String message;
    private LocalDateTime timestamp;
}
