package com.zzh.dream.study.base.core.concurrent.thread;

import cn.hutool.core.thread.NamedThreadFactory;
import com.google.common.util.concurrent.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.future 的详解和线程的提交方式
 * 2.guava 的future 解析 和线程异步回调
 * 3.加强版的Future - CompletableFuture 可以把这个接口理解为一个任务的某个阶段。所以多个 CompletionStage 链接在一起就是一个任务链。前一个任务完成后，下一个任务就会自动触发
 *
 */
public class TestFuture {

    public static void main(String[] args) throws Exception {
        //1.基础提交方式
        futureSubmit();

        //2.guava 提交回调异步
//        guavaFutureSubmit();

        //3completableFuture
//        completableFuture();
    }

    /**
     * 提交方式
     */
    private static void futureSubmit(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new NamedThreadFactory("name", true), new ThreadPoolExecutor.AbortPolicy());

        //1.我是无返回值的提交
        executor.execute(() ->{
            System.out.println("我是无返回值的提交...");
        });

        //2.我是submit Callable<T>的提交 -- <T> Future<T> submit(Callable<T> task)
        Future<String> result = executor.submit(() -> {
            System.out.println("我是无返回值的提交...");
            return "我是submit Callable<T>的返回值";
        });

        //3.我是submit 返回null，的提交  Future<?> submit(Runnable task)
        Future<?> submit = executor.submit(() -> {
            System.out.println("我是无返回值的提交...");
        });
        //submit.get(); == null

        //4.我是submit Runnable的提交 -- <T> Future<T> submit(Runnable task, T result)  返回的是传入的值
        AtomicInteger atomicInteger = new AtomicInteger();
        Future<AtomicInteger> result1 = executor.submit(() -> {
            System.out.println("我是无返回值的提交...");
            atomicInteger.set(5201314);
        },atomicInteger);

        executor.shutdown();
    }

    /**
     * guava 真正的异步线程，1.提交方式 ,2.异步执行成功之后会回调
     * 使用：新增了一个 addListenter 方法，入参是一个 Runnable 的任务类型和一个线程池。
     */
    private static void guavaFutureSubmit() throws InterruptedException {

        /**
         * 1.监听器的方式addListener
         */
        //1.首先创建线程池的方式变了，需要用 Guava 里面的 MoreExecutors 方法装饰一下：
        System.out.println("监听器的方式...");
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        //2.然后用装饰后的 executor 调用 submit 方法（任意一种），就会返回 ListenableFuture
        ListenableFuture<String> listenableFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "-女神：我开始化妆了，好了我叫你。");
            TimeUnit.SECONDS.sleep(5);
            return "化妆完毕了。";
        });

        //3.拿到这个 ListenableFuture 之后，我们就可以在上面注册监听
        listenableFuture.addListener(() ->{
            try {
                System.out.println(Thread.currentThread().getName()+"-future的内容:" + listenableFuture.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },executor);

        System.out.println(Thread.currentThread().getName()+"-等女神化妆的时候可以干点自己的事情。");
//        Thread.currentThread().join();
        executor.shutdown();

        /**
         * 2.FutureCallback
         */
        System.out.println("FutureCallback的方式...");

        //1.首先创建线程池的方式变了，需要用 Guava 里面的 MoreExecutors 方法装饰一下：
        ListeningExecutorService executor2 = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        //2.然后用装饰后的 executor 调用 submit 方法（任意一种），就会返回 ListenableFuture
        ListenableFuture<String> listenableFuture2 = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "-女神：我开始化妆了，好了我叫你。");
            TimeUnit.SECONDS.sleep(5);
            return "化妆完毕了。";
        });

        //3.回调 --成功操作，失败的操作
        Futures.addCallback(listenableFuture2, new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(Thread.currentThread().getName()+"-future的内容:" + result);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(Thread.currentThread().getName()+"-女神放你鸽子了。");
                        throwable.printStackTrace();
                    }
                }
                , executor2);

        System.out.println(Thread.currentThread().getName()+"-等女神化妆的时候可以干点自己的事情。");
//        Thread.currentThread().join();
        executor2.shutdown();

    }

    /**
     * CompletableFuture
     * 可以把这个接口理解为一个任务的某个阶段。所以多个 CompletionStage 链接在一起就是一个任务链。前一个任务完成后，下一个任务就会自动触发
     */
    private static void completableFuture() throws Exception {
        //设置任务
        //temp的修改内容
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-女神：我开始化妆了，好了我叫你。");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "化妆完毕了。";
        });

        completableFuture.whenComplete((result,exception) ->{
            if (exception == null) {
                System.out.println(Thread.currentThread().getName() + result);
            } else {
                System.out.println(Thread.currentThread().getName() + "女神放你鸽子了。");
                exception.printStackTrace();
            }
        });
        System.out.println(Thread.currentThread().getName() + "-等女神化妆的时候可以干点自己的事情。");
//        Thread.currentThread().join();

        /**
         * 接下来主要看看 CompletableFuture 对于异常的处理。我觉得非常的优雅
         */

        System.out.println("接下来主要看看 CompletableFuture 对于异常的处理。我觉得非常的优雅...");
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-女神：我开始化妆了，好了我叫你。");
            throw new RuntimeException("男神约我看电影了，我们下次再约吧，你是个好人。");
        }).handleAsync((result, exception) -> {
            if (exception != null) {
                System.out.println(Thread.currentThread().getName() + "-女神放你鸽子了！");
                return exception.getCause();
            } else {
                return result;
            }
        }).thenApplyAsync((returnStr) -> {
            System.out.println(Thread.currentThread().getName() + "-" + returnStr);
            return returnStr;
        });
        System.out.println(Thread.currentThread().getName() + "-等女神化妆的时候可以干点自己的事情。处理冲突");
        Thread.currentThread().join();
        System.out.println("temp的修改内容...");
    }


}
