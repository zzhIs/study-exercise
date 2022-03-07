package com.zzh.dream.rabbitmqcore.helper;

import cn.hutool.json.JSONUtil;
import com.zzh.dream.rabbitmqcore.entity.MessageQueueLog;
import com.zzh.dream.rabbitmqcore.service.MessageQueueLogService;
import com.zzh.dream.rabbitmqcore.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

/**
 * @description: 消息发送帮助类
 * @author: zhangzihao
 * @date: 15/01/2022
 **/
@Component
public class ProductHelper {

    private static final Logger log = LoggerFactory.getLogger(ProductHelper.class);

    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageQueueLogService messageQueueLogService;

    public ProductHelper() {
    }

    public void sendMessageToMq(MessageQueueLog messageQueueLog){
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            log.info("事务同步器已开启，延迟执行");
            TransactionSynchronizationManager.registerSynchronization(new ProductHelper.RmqSynchronization(messageQueueLog));
        } else {
            log.info("事务同步器未开启，直接执行任务");
            (new ProductHelper.RmqSynchronization(messageQueueLog)).run();
        }
    }

    public class RmqSynchronization extends TransactionSynchronizationAdapter {
        private MessageQueueLog message;

        public RmqSynchronization(MessageQueueLog dto) {
            this.message = dto;
        }

        public void afterCommit() {
            this.run();
        }

        private void run() {
            ProductHelper.log.info("开始推送消息到MQ");
            String id = this.message.getId();
            CorrelationData correlationData = new CorrelationData(StringUtils.isEmpty(id) ? UUIDUtils.get32UUID() : id);
            ProductHelper.this.rabbitTemplate.convertAndSend(this.message.getExchangeName(), this.message.getRoutingKey(),
                    JSONUtil.toJsonStr(this.message),correlationData);
        }
    }
}
