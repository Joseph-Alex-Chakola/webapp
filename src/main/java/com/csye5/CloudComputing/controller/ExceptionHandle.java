package com.csye5.CloudComputing.controller;

import com.csye5.CloudComputing.Exception.UserAlreadyExistsException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void MethodNotFound(NoHandlerFoundException e) {
        System.out.println(e.getMessage());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void MethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        System.out.println(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void MethodArgumentNotValid(MethodArgumentNotValidException e) {
        System.out.println(e.getMessage());
    }

    @ExceptionHandler({UserAlreadyExistsException.class, HttpMessageNotReadableException.class, NoSuchElementException.class,IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void UserAlreadyExists(Exception e) {
        System.out.println(e.getMessage());
    }
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void MissingRequestHeader(MissingRequestHeaderException e) {
        System.out.println(e.getMessage());
    }

}
