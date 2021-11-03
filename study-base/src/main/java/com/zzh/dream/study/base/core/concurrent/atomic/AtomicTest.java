package com.zzh.dream.study.base.core.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 原子类
 * @author: zhangzihao
 * @date: 23/10/2021
 **/
public class AtomicTest {
    public static int total = 0;
    public static AtomicInteger totalAtomic = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        add();
        atomicAdd();
    }


    /**
     * 创建 1000 个线程分别对 total 进行累加
     */
    private static void add() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10000);

        for (int i = 0; i <10000 ; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    total = total+1;
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("非原子累加："+total);
    }

    /**
     * 创建 1000 个线程分别对 total 进行累加
     */
    private static void atomicAdd() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i <10000 ; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    totalAtomic.getAndAdd(1);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("原子累加："+totalAtomic);
    }
}
