package org.example.general.handler;

import org.example.general.restresponse.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestResponse<String>> handleUserNotFoundException(UserNotFoundException ex) {
        RestResponse<String> response = RestResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestResponse<String>> handleAuthenticationException(AuthenticationException ex) {
        RestResponse<String> response = RestResponse.error(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(APIKeyNotFoundException.class)
    public ResponseEntity<RestResponse<String>> handleAPIKeyNotFoundException(APIKeyNotFoundException ex) {
        RestResponse<String> response = RestResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<String>> handleGeneralException(Exception ex) {
        RestResponse<String> response = RestResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ThisUserAlreadyExistsException.class)
    public ResponseEntity<RestResponse<String>> handleThisUserAlreadyExistsException(ThisUserAlreadyExistsException ex) {
        RestResponse<String> response = RestResponse.error(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}