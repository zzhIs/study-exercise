package com.zzh.dream.rabbitmqproducer.publish;

import cn.hutool.json.JSONUtil;
import com.zzh.dream.rabbitmqcore.constant.MessageQueueConstant;
import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqcore.helper.RmqHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 发送延时消息==交换机和队列依旧选择目标交换机和队列，过期后会自动转发到死信队列
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
@Component
public class DelayPublishBiz {

    @Autowired()
    private RmqHelper rmqHelper;

    /**
     * 发送消息到MQ
     *
     * @param messageQueueDTO
     */
    public void sendMq(MessageQueueDTO messageQueueDTO) {
        messageQueueDTO.setExchangeName(MessageQueueConstant.POLL_PAY_STATUS_EXCHANGE)
                .setRoutingKey(MessageQueueConstant.POLL_PAY_STATUS_ROUTE_KEY);
        rmqHelper.push(messageQueueDTO);
    }

}
