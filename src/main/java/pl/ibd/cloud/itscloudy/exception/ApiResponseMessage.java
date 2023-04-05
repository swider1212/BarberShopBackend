package pl.ibd.cloud.itscloudy.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiResponseMessage(
        String message,
        HttpStatus httpStatus,
        LocalDateTime timestamp,
        Throwable throwable) {
}
