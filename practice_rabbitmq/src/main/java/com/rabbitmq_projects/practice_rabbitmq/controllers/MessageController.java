package com.rabbitmq_projects.practice_rabbitmq.controllers;

import com.rabbitmq_projects.practice_rabbitmq.producers.RabbitMqProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {
    private final RabbitMqProducer rabbitMqProducer;


    @GetMapping
    public String sendMessage() {
        return "Hello World!";
    }

    @PostMapping("publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitMqProducer.sendMessage(message);
        return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
    }

}
