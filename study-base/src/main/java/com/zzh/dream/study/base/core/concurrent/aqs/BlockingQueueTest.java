package com.zzh.dream.study.base.core.concurrent.aqs;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 阻塞队列
 * @author: zhangzihao
 * @date: 25/10/2021
 **/
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        linkedBlockingQueue();
    }

    private static void linkedBlockingQueue() throws InterruptedException {
        BlockingQueue<Thread> blockingQueue = new LinkedBlockingQueue();

        //队列新增，超时与非超时
        blockingQueue.put(new Thread("take"));
        blockingQueue.offer(new Thread("poll"),1000, TimeUnit.MILLISECONDS);

        //阻塞队列获取，超时与非超时
        Thread take = blockingQueue.take();
        Thread poll = blockingQueue.poll(1000, TimeUnit.MILLISECONDS);

        System.out.println(take.getName());
        System.out.println(poll.getName());
    }
}
