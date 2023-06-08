package org.example.general.handler;

public class ThisUserAlreadyExistsException extends RuntimeException {

    public ThisUserAlreadyExistsException() {
        super("This user already exists");
    }
}
