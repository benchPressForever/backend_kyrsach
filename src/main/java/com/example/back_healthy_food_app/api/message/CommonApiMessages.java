package com.example.back_healthy_food_app.api.message;

public class CommonApiMessages {

    // StringMessage - строковое сообщение
    public record StringMessage(String status) {}

    // ErrorMessage - сообщение об ошибке
    public record ErrorMessage(String code, String details) {}

    // StatusMessage - сообщение статуса
    public record StatusMessage(String status,String host,String protocol) {}

}
