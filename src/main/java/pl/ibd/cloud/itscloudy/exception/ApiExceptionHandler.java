package pl.ibd.cloud.itscloudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public final class ApiExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ResponseEntity<ApiResponseMessage> handleLonelySeatException(EntityNotFoundException e) {
        HttpStatus resultStatus = NOT_FOUND;

        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<ApiResponseMessage> handleConflictException(ConflictException e) {

        return ResponseEntity
                .status(CONFLICT)
                .body(createApiException(e, CONFLICT));
    }

    private static <T extends InsaneScissorsException> ApiResponseMessage createApiException(T e, HttpStatus resultStatus) {

        return new ApiResponseMessage(
                e.getMessage(),
                resultStatus,
                LocalDateTime.now(),
                e
        );
    }
}
