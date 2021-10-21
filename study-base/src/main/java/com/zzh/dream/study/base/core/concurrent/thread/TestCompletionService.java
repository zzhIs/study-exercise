package com.zzh.dream.study.base.core.concurrent.thread;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 学习自：https://juejin.cn/post/6861505662741676039#heading-3
 *
 * CompletionService 讲解
 * 1.存在的问题:获取返回值严格按照执行顺序，第一个没有执行完，则即使第二个执行完了也会一直等待
 * 2.CompletionService 解决  用起来非常的方便，只需要用 ExecutorCompletionService 把线程池包起来 ，submit 方法提交
 *
 * 原理：
 *  CompletionService将线程池包装起来，执行submit方法，任务执行完成后，就会被放到队列里面去了。
 *  也就是说队列里面的task都是已经done了的task，而这个 task 就是一个个 future。
 *  如果调用 queue 的 task 方法，就是阻塞等待。等到的一定是就绪了的 future，调用 get 就能立马获得结果。
 *
 */
public class TestCompletionService {

    public static void main(String[] args) throws Exception {
        //存在的问题:获取返回值严格按照执行顺序，第一个没有执行完，则即使第二个执行完了也会一直等待
        test1();

        //CompletionService 解决  用起来非常的方便，只需要用 ExecutorCompletionService 把线程池包起来 ，submit 方法提交
        test2();
    }

    private static void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService =
                new ExecutorCompletionService<>(executorService);
        System.out.println("约几个妹子一起吃个饭吧。");
        completionService.submit(() -> {
            System.out.println("小红：好的，哥哥。我化妆要2个小时。等一下哦。");
            TimeUnit.SECONDS.sleep(15);
            System.out.println("小红：我2个小时准时化好了，哥哥来接我吧。");
            return "小红化完了。";
        });
        completionService.submit(() -> {
            System.out.println("小媛：好的，哥哥。我化妆要30分钟。等一下哦。");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("小媛：我30分钟准时化好了，哥哥来接我吧。");
            return "小媛化完了。";
        });
        completionService.submit(() -> {
            System.out.println("小花：好的，哥哥。我化妆要1个小时。等一下哦。");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("小花：我1个小时准时化好了，哥哥来接我吧。");
            return "小花化完了。";
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完,等着吧。");
        //循环3次是因为上面提交了3个异步任务
        for (int i = 0; i < 3; i++) {
            String returnStr = completionService.take().get();
            System.out.println(returnStr + "我去接她");
        }
        Thread.currentThread().join();
    }

    private static void test1() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> list = new ArrayList<>();
        System.out.println("约几个妹子一起吃个饭吧。");
        Future<String> future_15 = executorService.submit(() -> {
            System.out.println("小红：好的，哥哥。我化妆要2个小时。等一下哦。");
            TimeUnit.SECONDS.sleep(15);
            System.out.println("小红：我2个小时准时化好了，哥哥来接我吧。");
            return "小红化完了。";
        });
        list.add(future_15);
        Future<String> future_5 = executorService.submit(() -> {
            System.out.println("小媛：好的，哥哥。我化妆要30分钟。等一下哦。");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("小媛：我30分钟准时化好了，哥哥来接我吧。");
            return "小媛化完了。";
        });
        list.add(future_5);

        Future<String> future_10 = executorService.submit(() -> {
            System.out.println("小花：好的，哥哥。我化妆要1个小时。等一下哦。");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("小花：我1个小时准时化好了，哥哥来接我吧。");
            return "小花化完了。";
        });
        list.add(future_10);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完,等着吧。");
        for (Future<String> future : list) {
            System.out.println(future.get()+"我去接她。");
        }
        Thread.currentThread().join();
    }

}
