package com.app.comparetool.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorResponse {

    private String message;
    private LocalDateTime timeStamp;
    private String errDetails;

    public ErrorResponse(String message, String errDetails) {
        this.message = message;
        this.errDetails = errDetails;
    }

}
