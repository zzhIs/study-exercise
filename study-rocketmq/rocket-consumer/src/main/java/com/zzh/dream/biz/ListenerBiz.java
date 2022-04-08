package com.zzh.dream.biz;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 07/04/2022
 **/
@Component
@RocketMQMessageListener(consumerGroup = "MyConsumerGroup", topic = "SpringBootTopic")
public class ListenerBiz implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("Received message : "+ message);
    }

}

