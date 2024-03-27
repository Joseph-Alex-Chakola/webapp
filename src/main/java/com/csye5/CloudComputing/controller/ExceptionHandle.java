package com.csye5.CloudComputing.controller;

import com.csye5.CloudComputing.Exception.UserAlreadyExistsException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void MethodNotFound(NoHandlerFoundException e) {
        logger.error("Method Not Found: " + e.getMessage());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void MethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        logger.error("Method Not Allowed: " + e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void MethodArgumentNotValid(MethodArgumentNotValidException e) {
        logger.error("Method Argument Not Valid: " + e.getMessage());
    }

    @ExceptionHandler({UserAlreadyExistsException.class, HttpMessageNotReadableException.class, NoSuchElementException.class,IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void UserAlreadyExists(Exception e) {
        logger.error("User Already Exists: " + e.getMessage());
    }
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void MissingRequestHeader(MissingRequestHeaderException e) {
        logger.error("Missing Request Header: " + e.getMessage());
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void AccessDenied(AccessDeniedException e) {
        logger.error("Access Denied: " + e.getMessage());
    }

}
