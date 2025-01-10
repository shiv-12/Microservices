package com.project.rest.exception;

public class UserResponse {

    private final int status;
    private final String message;

    public UserResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
