package com.zzh.dream.study.base.rabbitmq.product;//package com.zzh.dream.rabbitmq.product;
//
//import com.zzh.dream.rabbitmq.configuration.DlxModelConfiguration;
//import com.zzh.dream.rabbitmq.configuration.PublishSubscribeConfiguration;
//import com.zzh.dream.rabbitmq.configuration.RoutingModelConfiguration;
//import com.zzh.dream.rabbitmq.configuration.SimpleModelConfiguration;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.ReturnedMessage;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@Slf4j
//@RestController
//@RequestMapping("/rabbit")
//public class RabbitMqController {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @GetMapping("/simple")
//    public String simple(){
//        rabbitTemplate.convertAndSend("", SimpleModelConfiguration.QUEUE_TEST_SIMPLE_MODEL,"测试简单模式的消息...");
//        return "消息已经发送";
//    }
//
//    /**
//     * 交换机为空
//     * @return
//     */
//    @GetMapping("/work_queue")
//    public String workQueue(){
//        rabbitTemplate.convertAndSend("", SimpleModelConfiguration.QUEUE_TEST_SIMPLE_MODEL,"测试工作队列模式的消息...");
//        return "消息已经发送";
//    }
//
//    /**
//     * 发布订阅模式--队列为为空
//     * @return
//     */
//    @GetMapping("/fanout_queue")
//    public String fanoutQueue(){
//        rabbitTemplate.convertAndSend(PublishSubscribeConfiguration.EXCHANGE_TEST_FANOUT_MODEL, "","测试发布订阅模式的消息...");
//        return "消息已经发送";
//    }
//
//    /**
//     * 路由模式
//     * @return
//     */
//    @GetMapping("/routing_queue")
//    public String routingQueue(){
//        rabbitTemplate.convertAndSend(RoutingModelConfiguration.EXCHANGE_TEST_ROUTING_MODEL, "order","测试发布路由模式的消息...");
//        return "消息已经发送";
//    }
//
//
//    /**
//     * 可靠消息发送,使用简单模式测试
//     *
//     * 消息失败的原因：
//     * 1. 消息发送过程导致的消息丢失：生产者将消息发送给交换机时，可能由于网络波动等原因导致消息不能正确到达交换机，导致消息丢失。
//     *      解决方案：发送方确认机制，即消息正确到达交换机后，RabbitMQ向生产者发送确认消息
//     *
//     * 2. 路由消息时导致的消息丢失：当消息到达交换机后，没有相应的队列与交换机绑定就会导致消息丢失。
//     *
//     *
//     * 3. 消费消息时导致消息丢失：默认情况下，消费者从队列中获取到消息后会直接确认签收，即自动签收，签收确认后消息会从队列中移除。倘若在消费者在处理收到的消息时发生宕机或者发生程序异常，就会导致消息的丢失
//     *
//     *
//     * 4. 消息未持久化导致的消息丢失
//     * RabbitMQ提供了交换机、队列、消息的持久化机制。当RabbitMQ宕机时保证数据不会丢失。
//     *
//     *
//     * @return
//     */
//    @GetMapping("/reliable_message")
//    public String reliableMessage(){
//        /**
//         * 设置消息确认回调方法；
//         * @ack 为true时，表示投递成功；为false表示投递失败；
//         * @CorrelationData 为自定义反馈信息；
//         * @cause 为投递失败的原因；
//         */
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean isAck, String cause) {
//                if (isAck) {
//                    log.info("消息发送成功..消息id{}",correlationData.getId());
//
//                }else{
//                    log.info("消息发送失败..消息id{},错误原因{}",correlationData.getId(),cause);
//                }
//            }
//        });
//
//        //消息内容
//        String messageId = UUID.randomUUID().toString();
//        /**
//         * 1. 消息发送过程导致的消息丢失
//         * 正确的发布+消息确认
//         */
//        rabbitTemplate.convertAndSend(PublishSubscribeConfiguration.EXCHANGE_TEST_FANOUT_MODEL,
//                "member", (Object) "测试简单模式的消息...",new CorrelationData(messageId));
//
//        /**
//         * 1. 消息发送过程导致的消息丢失
//         * 错误的交换机+消息错误确认
//         */
////        rabbitTemplate.convertAndSend( "231231",
////                "member", (Object) "测试简单模式的消息...",new CorrelationData(messageId));
//        return "消息已经发送";
//    }
//
//
//    @GetMapping("/reliable_message_queue")
//    public String reliableMessageQueue(){
//
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean isAck, String cause) {
//                if (isAck) {
//                    log.info("消息发送成功..消息id{}",correlationData.getId());
//
//                }else{
//                    log.info("消息发送失败..消息id{},错误原因{}",correlationData.getId(),cause);
//                }
//            }
//        });
//
//        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//            @Override
//            public void returnedMessage(ReturnedMessage returnedMessage) {
//                System.out.println(returnedMessage.toString());
//            }
//        });
//
//        //消息内容
//        String messageId = UUID.randomUUID().toString();
//        rabbitTemplate.convertAndSend( PublishSubscribeConfiguration.EXCHANGE_TEST_FANOUT_MODEL,
//                "member", (Object) "测试简单模式的消息...",new CorrelationData(messageId));
//
//        return "消息已经发送";
//    }
//
//
//    /**
//     * 测试死信队列
//     * @return
//     */
//    @GetMapping("/dlx_message_queue")
//    public String dlxMessageQueue(){
//        rabbitTemplate.convertAndSend(DlxModelConfiguration.EXCHANGE_TEST_COMMON_MODEL,"a","这是一条模拟死信的消息");
//        return "消息已经发送";
//    }
//}
