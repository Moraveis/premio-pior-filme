package com.premiopiorfilme.premiopiorfilme.model;

import org.springframework.http.HttpStatus;

/**
 *
 * @author joao
 */
public class ApiError {
    private HttpStatus status;
    private String message;
    private String path;
 
    public ApiError(HttpStatus status, String message, String path) {
        super();
        this.status = status;
        this.message = message;
        this.path = path;
    }
    
    public ApiError(){}

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
