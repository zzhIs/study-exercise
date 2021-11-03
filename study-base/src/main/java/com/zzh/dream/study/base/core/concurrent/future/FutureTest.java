package com.zzh.dream.study.base.core.concurrent.future;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 23/10/2021
 **/
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future<String> future = threadPool.submit((Callable<String>) () -> {
            return "异步执行完了";
        });

        try {
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread thread = new Thread();
        thread.start();
        System.out.println(thread.getName()+"阻塞前....");
        LockSupport.park();
        Thread.sleep(5000);
        LockSupport.unpark(thread);
    }

}
