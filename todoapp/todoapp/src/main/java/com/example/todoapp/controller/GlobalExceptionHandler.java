package com.example.todoapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.todoapp.model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class) 
    public ResponseEntity<ApiResponse<String>> 
handleTodoNotFound(TodoNotFoundException ex) { 
        ApiResponse<String> response = new 
ApiResponse<>("error", ex.getMessage(), null); 
        return new ResponseEntity<>(response, 
HttpStatus.NOT_FOUND); 
    }

@ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity<ApiResponse<String>> 
handleValidationErrors(MethodArgumentNotValidException ex) { 
        String errorMsg = 
ex.getBindingResult().getFieldError().getDefaultMessage(); 
        ApiResponse<String> response = new 
ApiResponse<>("error", errorMsg, null); 
        return new ResponseEntity<>(response, 
HttpStatus.BAD_REQUEST); 
    } 
}