package com.zzh.dream.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @description: Producer端发送同步消息
 * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
 * @author: zhangzihao
 * @date: 07/04/2022
 **/
public class SycnProducer {

    public static final String NAME_SERVER_ADDR = "127.0.0.1:9876";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("sync_group");
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        producer.start();

        for (int i = 0; i <100 ; i++) {
            Message message = new Message("zzhSycnTopic","TagA","",0,("zzhTest"+i).getBytes(RemotingHelper.DEFAULT_CHARSET),true);
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }

}
