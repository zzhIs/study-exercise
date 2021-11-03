package com.zzh.dream.study.base.core.concurrent.thread;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

public class TestThreadPool {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService =  Executors.newFixedThreadPool(10);

        ExecutorService executorService1 = new ThreadPoolExecutor(
                5,
                10,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new MyRejectPolicy());
        for (int i = 0; i <10 ; i++) {
            executorService.execute(()->{
                System.out.println("线程执行...");
            });
        }


        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        // 创建并执行在给定延迟后启用的一次性操作。
        ScheduledFuture<?> schedule = executor.schedule(() -> { System.out.println(""); return ""; }, 1000, TimeUnit.MILLISECONDS);
        schedule.get();

        //创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，然后在 initialDelay+period 后执行
        ScheduledFuture<?> schedule1 = executor.scheduleAtFixedRate(() -> {}, 1000,2000, TimeUnit.MILLISECONDS);

        //创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟
        ScheduledFuture<?> schedule2 = executor.scheduleWithFixedDelay(() -> {}, 1000,2000, TimeUnit.MILLISECONDS);

        Map<String,String> map = new HashMap<>(11);
        map.put("123","123");
        map.get("123");

        Map<String,String> concurrentHashMap = new ConcurrentHashMap<>(11);
        concurrentHashMap.put("123","123");
        concurrentHashMap.get("123");

        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("123",null);
    }


    public static class MyRejectPolicy implements RejectedExecutionHandler {

        public MyRejectPolicy() { }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println("自定义拒绝策略执行...");
        }
    }


}
