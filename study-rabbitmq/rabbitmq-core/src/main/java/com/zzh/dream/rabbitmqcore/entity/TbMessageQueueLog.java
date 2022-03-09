package com.zzh.dream.rabbitmqcore.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "message_queue_log")
@Data
@Accessors(chain = true)
public class TbMessageQueueLog {
    private String id;

    private String referenceId;

    private String referenceType;

    private String exchangeName;

    private String routingKey;

    private String message;

    private Date confirmTime;

    private Integer consumeNum;

    private String failCause;

    private String status;

    private String isPushed;

    private String isFailNotify;

    private String isDeleted;

    private String tenantId;

    private String appId;

    private String crtUser;

    private Date crtTime;

    private String updUser;

    private Date updDate;

}