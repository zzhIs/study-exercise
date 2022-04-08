package com.zzh.dream.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 07/04/2022
 **/
public class TransactionProducer {

    public static void main(String[] args) throws Exception{
        TransactionMQProducer producer = new TransactionMQProducer("TransactionGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setExecutorService(Executors.newFixedThreadPool(10));
        producer.setTransactionListener(new TransactionListenerImpl());
        producer.start();

        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTest1234", tags[i % tags.length], "KEY" + i, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                //使用事务方式提交
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}
