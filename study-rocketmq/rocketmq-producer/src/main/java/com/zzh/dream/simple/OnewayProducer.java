package com.zzh.dream.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @description: 单向发送消息
 * 主要用在不特别关心发送结果的场景，例如日志发送
 * @author: zhangzihao
 * @date: 07/04/2022
 **/
public class OnewayProducer {
    public static final String NAME_SERVER_ADDR = "127.0.0.1:9876";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("sync_group");
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        producer.start();

        for (int i = 0; i <100 ; i++) {
            Message message = new Message("zzhSycnTopic","TagA","",0,("zzhTest"+i).getBytes(RemotingHelper.DEFAULT_CHARSET),true);
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(message);
        }
        producer.shutdown();
    }


    public static void main1(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("sync_group");
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        producer.start();
        Message message = new Message("zzhSycnTopic","TagA","",0,("zzhTest").getBytes(RemotingHelper.DEFAULT_CHARSET),true);
        // 发送单向消息，没有任何返回结果
        producer.sendOneway(message);
        producer.shutdown();
    }
}
