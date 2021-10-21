package com.zzh.dream.study.base.core.concurrent.aqs;

import java.util.concurrent.locks.ReentrantLock;


public class TestReentrantLock {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock(true);

        lock.lock();

        //同步逻辑

        lock.unlock();
    }

}






