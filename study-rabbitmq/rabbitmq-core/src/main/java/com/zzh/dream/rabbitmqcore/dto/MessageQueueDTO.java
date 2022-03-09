package com.zzh.dream.rabbitmqcore.dto;

public class MessageQueueDTO {
    private String id;
    private String referenceId;
    private String referenceType;

    private String exchangeName;
    private String routingKey;
    private String message;

    private Long consumeNum;

    public MessageQueueDTO() {
    }

    public String getId() {
        return this.id;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public String getReferenceType() {
        return this.referenceType;
    }

    public String getExchangeName() {
        return this.exchangeName;
    }

    public String getRoutingKey() {
        return this.routingKey;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getConsumeNum() {
        return this.consumeNum;
    }

    public MessageQueueDTO setId(String id) {
        this.id = id;
        return this;
    }

    public MessageQueueDTO setReferenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public MessageQueueDTO setReferenceType(String referenceType) {
        this.referenceType = referenceType;
        return this;
    }

    public MessageQueueDTO setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
        return this;
    }

    public MessageQueueDTO setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
        return this;
    }

    public MessageQueueDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public MessageQueueDTO setConsumeNum(Long consumeNum) {
        this.consumeNum = consumeNum;
        return this;
    }
}