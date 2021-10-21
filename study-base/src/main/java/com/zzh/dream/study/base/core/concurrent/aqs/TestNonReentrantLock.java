package com.zzh.dream.study.base.core.concurrent.aqs;


public class TestNonReentrantLock  {

private static NonReentrantLock nonReentrantLock = new NonReentrantLock();

public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
        Thread thread = new Thread(() -> {
            nonReentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                nonReentrantLock.unlock();
            }
        });
        thread.start();
    }
}
}






