package com.example.back_healthy_food_app.api.controller;


import com.example.back_healthy_food_app.api.message.CommonApiMessages.StringMessage;
import com.example.back_healthy_food_app.api.message.CommonApiMessages.StatusMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class StatusController {

    @GetMapping
    public StatusMessage status(HttpServletRequest request) throws UnknownHostException{
        return new StatusMessage("Server is runnung" , InetAddress.getLocalHost().getHostName(),request.getProtocol());
    }

    @GetMapping("/ping")
    public StringMessage ping(){
        return new StringMessage("Pong");
    }
}
