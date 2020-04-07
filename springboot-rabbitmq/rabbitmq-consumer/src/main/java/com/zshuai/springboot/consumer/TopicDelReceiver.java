package com.zshuai.springboot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.del")
public class TopicDelReceiver {
 
    @RabbitHandler
    public void process(Map testMessage) {

        System.out.println("TopicDelReceiver消费者收到消息  : " + testMessage.toString());
    }
}