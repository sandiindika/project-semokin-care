package com.semokin.app.adapter.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class WebResponse<T> {
    private HttpStatus statusCode;
    private String message;
    private T data;
}
