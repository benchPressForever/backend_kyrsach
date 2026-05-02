package com.example.back_healthy_food_app.errors;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Пользователь с такой почтой уже существует!");
    }
}
