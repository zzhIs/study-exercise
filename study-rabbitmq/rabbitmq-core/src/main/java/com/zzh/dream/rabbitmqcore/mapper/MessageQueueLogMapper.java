package com.zzh.dream.rabbitmqcore.mapper;

import com.zzh.dream.rabbitmqcore.entity.MessageQueueLog;
import java.util.List;

public interface MessageQueueLogMapper {
    int insert(MessageQueueLog record);

    List<MessageQueueLog> selectAll();
}