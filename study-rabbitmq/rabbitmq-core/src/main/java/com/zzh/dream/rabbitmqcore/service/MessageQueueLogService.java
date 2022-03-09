package com.zzh.dream.rabbitmqcore.service;

import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqcore.entity.TbMessageQueueLog;

import java.util.Date;
import java.util.List;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 15/01/2022
 **/
public interface MessageQueueLogService {

    TbMessageQueueLog getExists(String id);

    boolean tryConsume(String id);

    void firstConfirm(String id);

    void finishMessage(String id);

    TbMessageQueueLog failMessage(String id, String err);

    /**
     * 获取推送状态是未推送的消息
     *
     * @param date
     * @return
     */
    List<TbMessageQueueLog> getNotPushMqByTime(Date date);

    void confirmPushToMq(String id);

    void insertMessageLog(MessageQueueDTO dto);
}
