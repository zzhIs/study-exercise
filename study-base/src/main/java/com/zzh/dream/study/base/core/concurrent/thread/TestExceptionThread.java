package com.zzh.dream.study.base.core.concurrent.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的异常线程的处理
 * 当执行方式是execute时,可以看到堆栈异常的输出。
 * 当执行方式是submit时,堆栈异常没有输出。但是调用Future.get()方法时，可以捕获到异常。
 * 不会影响线程池里面其他线程的正常执行。
 * 线程池会把这个线程移除掉，并创建一个新的线程放到线程池中。
 */
public class TestExceptionThread {

    public static void main(String[] args) throws Exception {
        ThreadPoolTaskExecutor executorService = buildThreadPoolTaskExecutor();
        executorService.execute(() -> sayHi("execute"));
        Thread.sleep(10);
        System.out.println("=================================");

        //异常封装在future中 只有future.get()时才会 捕捉到异常
        Future<?> submit = executorService.submit(() -> sayHi("submit"));
        submit.get();
    }

    private static void sayHi(String name) {
        String printStr = "【thread-name:" + Thread.currentThread().getName() + ",执行方式:" + name+"】";
        System.out.println(printStr);
        throw new RuntimeException(printStr + ",我异常啦!哈哈哈!");
    }

    private static ThreadPoolTaskExecutor buildThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executorService = new ThreadPoolTaskExecutor();
        executorService.setThreadNamePrefix("(公众号-why技术)-");
        executorService.setCorePoolSize(5);
        executorService.setMaxPoolSize(10);
        executorService.setQueueCapacity(1000);
        executorService.setKeepAliveSeconds(30);
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.initialize();
        return executorService;
    }
}
