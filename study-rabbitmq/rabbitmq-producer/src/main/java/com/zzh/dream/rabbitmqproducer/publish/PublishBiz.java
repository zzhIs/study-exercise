package com.zzh.dream.rabbitmqproducer.publish;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.zzh.dream.rabbitmqcore.constant.MessageQueueConstant;
import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqcore.helper.RmqHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
@Slf4j
@Component
public class PublishBiz {

    @Autowired
    private RmqHelper rmqHelper;

    /**
     * 发送消息到MQ
     *
     * @param messageQueueDTO
     */
    public void sendMq(MessageQueueDTO messageQueueDTO) {
        messageQueueDTO.setExchangeName(MessageQueueConstant.DEFAULT_DIRECT_EXCHANGE)
                .setReferenceType("type").setRoutingKey(MessageQueueConstant.DEFAULT_DIRECT_KEY);
        rmqHelper.push(messageQueueDTO);
        log.info("消息发送成功...{}",JSONUtil.toJsonStr(messageQueueDTO));
    }

}
