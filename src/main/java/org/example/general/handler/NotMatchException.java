package org.example.general.handler;

public class NotMatchException extends RuntimeException {
    public NotMatchException() {
        super("User and password do not match");
    }
}
