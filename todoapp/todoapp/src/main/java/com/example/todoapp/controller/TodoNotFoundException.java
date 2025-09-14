package com.example.todoapp.controller;

public class TodoNotFoundException extends 
RuntimeException { 
    public TodoNotFoundException(String message) { 
        super(message); 
    } 
} 