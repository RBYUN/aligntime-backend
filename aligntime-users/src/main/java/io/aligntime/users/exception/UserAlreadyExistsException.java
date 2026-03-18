package io.aligntime.users.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super("User with email '" + email + "' already exists");
    }

    public String getMessage() {
        return super.getMessage();
    }
}