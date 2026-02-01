package com.example.taskmanagerapi.Domain.Exception;

public class InvalidTaskException extends RuntimeException {
    public InvalidTaskException(String message) {
        super(message);
    }
}