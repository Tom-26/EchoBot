package com.example.VkBot.service;

import org.springframework.stereotype.Service;

@Service
public class VkBotService {
    public String processMassage(String message){
        return "Вы сказали: " + message;
    }
}
