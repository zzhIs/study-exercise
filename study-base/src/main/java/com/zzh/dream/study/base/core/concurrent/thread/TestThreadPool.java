package com.zzh.dream.study.base.core.concurrent.thread;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

public class TestThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService =  Executors.newFixedThreadPool(10);

        ExecutorService executorService1 = new ThreadPoolExecutor(
                5,
                10,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                new DefaultThreadFactory("PoolName"),
                new MyRejectPolicy());
        for (int i = 0; i <10 ; i++) {
            executorService.execute(()->{
                System.out.println("线程执行...");
            });
        }

    }


    public static class MyRejectPolicy implements RejectedExecutionHandler {

        public MyRejectPolicy() { }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println("自定义拒绝策略执行...");
        }
    }


}
