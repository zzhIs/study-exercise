package com.zzh.dream.rabbitmqcore.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqcore.entity.TbMessageQueueLog;
import com.zzh.dream.rabbitmqcore.helper.RmqHelper;
import com.zzh.dream.rabbitmqcore.service.MessageQueueLogService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @description: 定时任务推送未推送的消息，或消息没有发送到MQ的消息
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
public class MqCompensatePushJob {

    private static final Logger log = LoggerFactory.getLogger(MqCompensatePushJob.class);
    @Autowired
    private MessageQueueLogService messageQueueService;
    @Autowired
    private RmqHelper rmqHelper;
    @Autowired
    private ModelMapper modelMapper;

    public void reSend() {
        List<TbMessageQueueLog> mqLogs = this.messageQueueService.getNotPushMqByTime(new Date());
        if (CollUtil.isNotEmpty(mqLogs)) {
            mqLogs.stream().forEach(mqLog -> {
                try {
                    MessageQueueDTO dto = (MessageQueueDTO) this.modelMapper.map(mqLog, MessageQueueDTO.class);
                    log.info("尝试推送消息至MQ,detail:{}", JSONUtil.toJsonStr(dto));
                    this.rmqHelper.sendMessageToMq(dto);

                    //修改推送状态 --已推送
                    this.messageQueueService.confirmPushToMq(mqLog.getId());
                } catch (Exception var9) {
                    var9.printStackTrace();
                }
            });

        }
    }

}
