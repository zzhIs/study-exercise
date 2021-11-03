package com.zzh.dream.study.base.core.concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * @description: Future模式的高阶版本CompletableFuture，Future 是 JDK 1.5 时代的产物，Doug Lea 在 JDK 1.8 里面引入了新的 CompletableFuture，通过回调实现真正的异步编程
 * @author: zhangzihao
 * @date: 23/10/2021
 **/
public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("异步执行第一个任务...");
            return "异步执行第一个任务--执行完了";
        });

        completableFuture.whenComplete((result,exception) ->{
            if (exception == null) {
                System.out.println(Thread.currentThread().getName() + result);
                System.out.println("第一个异步任务执行完了，可以执行后续任务");
            } else {
                System.out.println(Thread.currentThread().getName() + "等第一个一步线程执行异常了。");
                exception.printStackTrace();
            }
        });


        System.out.println(Thread.currentThread().getName() + "等异步任务执行的时候可以干点别的事情。");
        Thread.currentThread().join();
    }

}
