package com.zzh.dream.rabbitmqcore.mapper;

import com.zzh.dream.rabbitmqcore.entity.TbMessageQueueLog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageQueueLogMapper extends Mapper<TbMessageQueueLog> {
    int insert(TbMessageQueueLog record);

    List<TbMessageQueueLog> selectAll();

    int tryConsume(@Param("id") String id);
}