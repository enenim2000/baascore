package com.sahaplus.baascore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime time = LocalDateTime.now();

}
