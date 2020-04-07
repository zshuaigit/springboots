package com.zshuai.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

/**
 * Created by zshuai
 *
 * TopicRabbit 主题交换机
 * @Date :2020/4/7 1:00 PM
 * @Version 1.0
 **/
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


    //将addQueue和topicExchange绑定,而且绑定的键值为topic.add 模拟添加
    //这样只要是消息携带的路由键是topic.add,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(addQueue()).to(exchange()).with(add);
    }

    //将delQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(delQueue()).to(exchange()).with("topic.#");
    }
}
