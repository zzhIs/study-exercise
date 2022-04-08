package com.zzh.dream.controller;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 07/04/2022
 **/
@RestController
@RequestMapping("mq")
public class ZzhMqController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public static final String TOPIC = "SpringBootTopic";


    //发送普通消息
    @PostMapping("/simple")
    public void sendSimple(@RequestParam("msg")String msg){
        rocketMQTemplate.convertAndSend(TOPIC,msg);
    }

    //发送事务消息
    @PostMapping("transaction")
    public void sendTransaction(@RequestParam("msg") String msg) throws Exception{
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i <100 ; i++) {
            Message<String> message = MessageBuilder.withPayload(msg).build();
            String destination =TOPIC+":"+tags[i % tags.length];
            SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message,destination);
            System.out.printf("%s%n", sendResult);
            Thread.sleep(10);
        }
    }
}
