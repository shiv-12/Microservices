package com.project.rest.exception;

import java.time.LocalDateTime;

public class UserResponse {

    private final int status;
    private final String message;
    private final LocalDateTime localDateTime;

    public UserResponse(int status, String message, LocalDateTime localDateTime) {
        this.status = status;
        this.message = message;
        this.localDateTime = localDateTime;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
