package com.zzh.dream.rabbitmqproducer.controller;

import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqproducer.publish.DelayPublishBiz;
import com.zzh.dream.rabbitmqproducer.publish.PublishBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
@RestController
@RequestMapping("/produce")
public class ProduceController {

    @Autowired
    private PublishBiz publishBiz;
    @Autowired
    private DelayPublishBiz delayPublishBiz;

    @PostMapping("/order")
    public String sendOrderMessage(@RequestBody MessageQueueDTO messageQueueDTO){
        publishBiz.sendMq(messageQueueDTO);
        return "success";
    }

    @PostMapping("/delay_order")
    public String sendDelayOrderMessage(@RequestBody MessageQueueDTO messageQueueDTO){
        delayPublishBiz.sendMq(messageQueueDTO);
        return "success";
    }
}
