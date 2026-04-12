package com.example.demo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173")
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public String sendMessage(String message) {
        return message;
    }
}
