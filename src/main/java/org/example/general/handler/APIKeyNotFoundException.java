package org.example.general.handler;

public class APIKeyNotFoundException extends RuntimeException {
    public APIKeyNotFoundException() {
        super("API key not found");
    }
}
