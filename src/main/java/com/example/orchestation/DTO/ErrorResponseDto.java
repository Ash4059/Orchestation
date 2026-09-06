package com.example.orchestation.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {
    
    private LocalDateTime timestamp;
    private int statusCode;
    private String message;
    private String error;
    private String path;

}
