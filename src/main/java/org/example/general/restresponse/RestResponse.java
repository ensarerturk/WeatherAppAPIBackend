package org.example.general.restresponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RestResponse<T> {
    private int status;
    private String message;
    private T data;

    public RestResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> RestResponse<T> success(int status, T data) {
        return new RestResponse<>(status, null, data);
    }

    public static <T> RestResponse<T> error(int status, String message) {
        return new RestResponse<>(status, message, null);
    }
}

