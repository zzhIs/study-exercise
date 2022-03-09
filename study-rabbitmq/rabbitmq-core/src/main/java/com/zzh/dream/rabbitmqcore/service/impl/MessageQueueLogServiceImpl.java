package com.zzh.dream.rabbitmqcore.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zzh.dream.rabbitmqcore.dto.MessageQueueDTO;
import com.zzh.dream.rabbitmqcore.entity.TbMessageQueueLog;
import com.zzh.dream.rabbitmqcore.enums.MessageQueueStatus;
import com.zzh.dream.rabbitmqcore.mapper.MessageQueueLogMapper;
import com.zzh.dream.rabbitmqcore.service.MessageQueueLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 15/01/2022
 **/
@Slf4j
@Service
public class MessageQueueLogServiceImpl implements MessageQueueLogService {

    @Autowired
    private MessageQueueLogMapper mapper;

    @Override
    public void insertMessageLog(MessageQueueDTO dto) {
        TbMessageQueueLog messageQueueLog = JSONUtil.toBean(JSONUtil.toJsonStr(dto), TbMessageQueueLog.class);
        messageQueueLog.setStatus(MessageQueueStatus.PREPARED.getCode())
                .setCrtTime(new Date()).setCrtUser("user_zzh").setIsDeleted("0");
        mapper.insert(messageQueueLog);
        log.info("消息插入数据库成功...{}", JSONUtil.toJsonStr(dto));
    }

    @Override
    public TbMessageQueueLog getExists(String id) {
        return mapper.selectOne((new TbMessageQueueLog()).setId(id).setIsDeleted("0"));
    }

    @Override
    public boolean tryConsume(String id) {
        if (StrUtil.isBlank(id)) {
            return false;
        } else {
            return this.mapper.tryConsume(id) > 0;
        }
    }

    @Override
    public void firstConfirm(String id) {
        TbMessageQueueLog log = this.getExists(id);
        if (ObjectUtil.isNull(log)) {
            throw new NullPointerException("消息不存在");
        } else if (!ObjectUtil.isNotNull(log.getConfirmTime())) {
            log.setConfirmTime(new Date());
            updateUser(log);
            mapper.updateByPrimaryKeySelective(log);
        }
    }



    @Override
    public void finishMessage(String id) {
        TbMessageQueueLog log = this.getExists(id);
        if (ObjectUtil.isNull(log)) {
            throw new NullPointerException("消息不存在");
        } else {
            log.setStatus(MessageQueueStatus.FINISHED.getCode()).setConsumeNum(log.getConsumeNum() + 1);
            updateUser(log);
            this.mapper.updateByPrimaryKeySelective(log);
        }
    }

    @Override
    public TbMessageQueueLog failMessage(String id, String err) {
        TbMessageQueueLog log = this.getExists(id);
        if (ObjectUtil.isNull(log)) {
            throw new NullPointerException("消息不存在");
        } else {
            log.setStatus(MessageQueueStatus.FAIL.getCode()).setFailCause(err).setConsumeNum(log.getConsumeNum() + 1);
            updateUser(log);
            this.mapper.updateByPrimaryKeySelective(log);
            return log;
        }
    }

    @Override
    public List<TbMessageQueueLog> getNotPushMqByTime(Date endTime) {
        Example example = new Example(TbMessageQueueLog.class);
        example.createCriteria().andEqualTo("isDeleted", "0").andEqualTo("isPushed", "0").andLessThanOrEqualTo("crtTime", endTime);
        return this.mapper.selectByExample(example);
    }

    @Override
    public void confirmPushToMq(String id) {
        log.debug("[{}]开始确认推送到MQ", id);
        TbMessageQueueLog ql = new TbMessageQueueLog();
        updateUser(ql);
        ql.setId(id).setIsPushed("1");
        this.mapper.updateByPrimaryKeySelective(ql);
        log.debug("[{}]已推送到MQ", id);
    }


    private void updateUser(TbMessageQueueLog log){
        log.setUpdDate(new Date()).setCrtUser("update");
    }
}
