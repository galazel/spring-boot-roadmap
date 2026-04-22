package com.rabbitmq_projects.practice_rabbitmq.consumers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqConsumer {

    @RabbitListener(queues = {"springboot-queue"})
    public void receiveMessage1(String message) {
        log.info("RabbitMqConsumer1 received message: {}", message);
    }
    @RabbitListener(queues = {"springboot-json-queue"})
    public void receiveMessage2(String message) {
        log.info("RabbitMqConsumer2 received message: {}", message);
    }

}
