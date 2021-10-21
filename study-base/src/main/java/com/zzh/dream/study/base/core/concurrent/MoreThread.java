package com.zzh.dream.study.base.core.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import io.lettuce.core.internal.Futures;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 23/09/2021
 **/
public class MoreThread {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        for (int i = 0; i <2 ; i++) {
            //执行逻辑
            latch.countDown();
        }
        latch.await();

        //限制执行的线程数
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i <2 ; i++) {
            semaphore.acquire();
            //执行逻辑
            semaphore.release();
        }

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i <2 ; i++) {
            //执行逻辑
            cyclicBarrier.await();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2, new NamedThreadFactory("name"));
        executorService.submit(()->{
            return "qwe";
        });
        executorService.execute(()->{
        });

        ExecutorService executorService2 = Executors.newCachedThreadPool( new NamedThreadFactory("name"));
        ExecutorService executorService3 = Executors.newSingleThreadExecutor( new NamedThreadFactory("name"));
        ExecutorService executorService4 = Executors.newWorkStealingPool( 2);
        executorService4.execute(()->{});


        List<Future<String>> resultList = new ArrayList();
        CompletionService completionService = new ExecutorCompletionService(executorService);
        Future submit = completionService.submit((Callable)() -> {
            //执行逻辑
            return "123123";
        });
        resultList.add(submit);
        for (Future<String> future : resultList) {
            String result = future.get(10,TimeUnit.SECONDS);
        }


        //真正异步回调的线程池
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<String> listenableFuture = (ListenableFuture<String>) listeningExecutorService.submit(() -> {
            System.out.println("开始执行多线程逻辑...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "执行结束...";
        });
        
//        Futures.addCallback(listenableFuture,new FutureCallback<String>(){
//            @Override
//            public void onSuccess(@Nullable String result) {
//                System.out.println("我是成功的回调...");
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.println("我是失败的回调...");
//            }
//        },executorService);

        //1.8异步执行
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始执行异步逻辑...");
            return "执行完毕...";
        });
        completableFuture.whenComplete((returnStr , exception) ->{
            if (exception == null) {
                System.out.println("正常执行...");
                System.out.println(Thread.currentThread().getName() + returnStr);
            } else {
                System.out.println(Thread.currentThread().getName() + "执行失败...");
                exception.printStackTrace();
            }
        });
        System.out.println("异步执行的时候做点别的...");
    }
}
