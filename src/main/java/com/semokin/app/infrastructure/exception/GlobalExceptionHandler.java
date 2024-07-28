package com.semokin.app.infrastructure.exception;

import com.semokin.app.adapter.dto.response.WebResponse;
import com.semokin.app.domain.exception.ResourceNoContentException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private ResponseEntity<WebResponse<?>> buildResponse(HttpStatus status, String message) {
        WebResponse<?> response = WebResponse.<Collections>builder()
                .statusCode(status)
                .message(message)
                .data(null)
                .build();
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ResourceNoContentException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<WebResponse<?>> handleResourceNoContentException(ResourceNoContentException e) {
        return buildResponse(HttpStatus.NO_CONTENT, e.getMessage());
    }
}
