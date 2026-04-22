package com.rabbitmq_projects.practice_rabbitmq.configs;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue springbootQueue() {
        return new Queue("springboot-queue");
    }
    @Bean
    public TopicExchange springbootExchange() {
        return new TopicExchange("springboot-exchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(springbootQueue()).to(springbootExchange()).with("springboot-routingkey");
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue("springboot-json-queue");
    }

    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue()).to(springbootExchange()).with("springboot_json_routingkey");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
