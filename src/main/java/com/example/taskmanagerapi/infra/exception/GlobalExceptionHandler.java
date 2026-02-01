package com.example.taskmanagerapi.infra.exception;

import com.example.taskmanagerapi.Domain.Exception.InvalidTaskException;
import com.example.taskmanagerapi.Domain.Exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(TaskNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidTaskException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidTask(InvalidTaskException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpected(Exception ex) {
        return "Une erreur interne est survenue";
    }
}