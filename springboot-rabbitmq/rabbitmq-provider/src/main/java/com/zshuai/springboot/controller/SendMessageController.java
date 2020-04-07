package com.zshuai.springboot.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zshuai
 *
 * @Date :2020/4/7 12:42 PM
 * @Version 1.0
 **/

@RestController
@RequestMapping("send")
public class SendMessageController {

    //使用RabbitTemplate,这提供了接收/发送等等方法
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 消息推送到server，有交换机及队列
     * @return
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：true
     * ConfirmCallback:     原因：null
     */
    @GetMapping("/directMessage")
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "testmessageACK";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: 添加操作！ ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> addMap = new HashMap<>();
        addMap.put("messageId", messageId);
        addMap.put("messageData", messageData);
        addMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.add", addMap);
        return "添加信息发送成功";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: del 操作，且发送给所有用户 ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> delMap = new HashMap<>();
        delMap.put("messageId", messageId);
        delMap.put("messageData", messageData);
        delMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.del", delMap);
        return "删除操作，且发送给所有topic开头的队列！";
    }


    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: 扇形交换机消息测试 ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "扇形交换机测试";
    }

    /**
     * 消息推送到server，但是在server里找不到交换机
     * @return
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：false
     * ConfirmCallback:     原因：channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'non-existent-exchange' in vhost '/zshuai', class-id=60, method-id=40)
     */
    @GetMapping("/TestMessageAck")
    public String TestMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return "ok";
    }

    /**
     * 消息推送到server，找到交换机，无匹配队列
     * @return
     * ReturnCallback:     消息：(Body:'{createTime=2020-04-07 14:39:34, messageId=3cafa5ad-0171-40ac-9ccb-e3d0751f8bb0, messageData=message: lonelyDirectExchange test message }' MessageProperties [headers={}, contentType=application/x-java-serialized-object, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, deliveryTag=0])
     * ReturnCallback:     回应码：312
     * ReturnCallback:     回应信息：NO_ROUTE
     * ReturnCallback:     交换机：lonelyDirectExchange
     * ReturnCallback:     路由键：TestDirectRouting
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：true
     * ConfirmCallback:     原因：null
     */

    @GetMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        return "ok";
    }



}
