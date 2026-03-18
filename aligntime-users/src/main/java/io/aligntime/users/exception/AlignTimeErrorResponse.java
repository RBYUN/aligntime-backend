package io.aligntime.users.exception;

import java.time.Instant;

public class AlignTimeErrorResponse {

    private int status;
    private String message;
    private Instant timestamp;

    public AlignTimeErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
