package com.zzh.dream.rabbitmqcore.enums;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 15/01/2022
 **/
public enum MessageQueueStatus {
    PREPARED("PREPARED", "待消费"),
    CONSUMING("CONSUMING", "消费中"),
    FINISHED("FINISHED", "结束"),
    FAIL("FAIL", "失败");

    private String code;
    private String name;

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    private MessageQueueStatus() {
    }

    private MessageQueueStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
