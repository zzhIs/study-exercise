package com.zzh.dream.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description: 发送异步消息
 * 异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应
 * @author: zhangzihao
 * @date: 07/04/2022
 **/
public class AsycnProducer {

    public static final String NAME_SERVER_ADDR = "127.0.0.1:9876";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("sync_group");
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        producer.setRetryTimesWhenSendAsyncFailed(0);
        producer.start();


        int messageCount = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);


        for (int i = 0; i < 100; i++) {
            final int index = i;
            Message message = new Message("zzhSycnTopic", "TagB", "", 0, ("zzhTestAsync" + i).getBytes(RemotingHelper.DEFAULT_CHARSET), true);

            //异步发送注册回调
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();

                }
            });
        }
        countDownLatch.wait();
        producer.shutdown();
    }
}
