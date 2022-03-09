package com.zzh.dream.rabbitmqcore.constant;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 08/03/2022
 **/
public class MessageQueueConstant {


    /******************************* 通用配置 *******************************/

    /**
     * 默认交换机
     */
    public static final String DEFAULT_DIRECT_EXCHANGE = "DIRECT_EXCHANGE";
    /**
     * 默认队列
     */
    public static final String DEFAULT_DIRECT_QUEUE= "DEFAULT_DIRECT_QUEUE";

    /**
     * 默认路由Key
     */
    public static final String DEFAULT_DIRECT_KEY= "DEFAULT_DIRECT_KEY";







    //================================以下是死信队列队列交换机路由key的常量==============================================
    /**
     * 轮询微信/支付宝支付状态队列交换机（延迟）
     */
    public static final String POLL_PAY_STATUS_EXCHANGE = "POLL_PAY_STATUS_EXCHANGE";
    /**
     * 轮询微信/支付宝支付状态队列（延迟）
     */
    public static final String POLL_PAY_STATUS_QUEUE = "POLL_PAY_STATUS_QUEUE";

    /**
     * 轮询微信/支付宝支付状态队列交换机路由key（延迟）
     */
    public static final String POLL_PAY_STATUS_ROUTE_KEY = "POLL_PAY_STATUS_ROUTE_KEY";


    /**
     * 轮询微信/支付宝支付状态队列交换机(死信)
     */
    public static final String POLL_PAY_STATUS_DEAD_EXCHANGE = "POLL_PAY_STATUS_DEAD_EXCHANGE";
    /**
     * 轮询微信/支付宝支付状态队列(死信)
     */
    public static final String POLL_PAY_STATUS_DEAD_QUEUE = "POLL_PAY_STATUS_DEAD_QUEUE";

    /**
     * 轮询微信/支付宝支付状态队列交换机路由key(死信)
     */
    public static final String POLL_PAY_STATUS_DEAD_ROUTE_KEY = "POLL_PAY_STATUS_DEAD_ROUTE_KEY";

}
