package com.zshuai.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {
    //绑定键
    public final static String add = "topic.add";
    public final static String del = "topic.del";
 
    @Bean
    public Queue addQueue() {
        return new Queue(TopicRabbitConfig.add);
    }
 
    @Bean
    public Queue delQueue() {
        return new Queue(TopicRabbitConfig.del);
    }
 
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }
 
 
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(addQueue()).to(exchange()).with(add);
    }
 
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(delQueue()).to(exchange()).with("topic.#");
    }
 
}