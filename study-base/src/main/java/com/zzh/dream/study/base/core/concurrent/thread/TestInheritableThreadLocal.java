package com.zzh.dream.study.base.core.concurrent.thread;

/**
 * @description: 子线程共享父线程的ThreadLocal变量的工具类
 * 原理：是在子线程创建的一瞬间才将父线程inheritableThreadLocals 变量的值赋值给子线程，
 * 一旦子线程创建成功后，用户再次去修改了父线程inheritableThreadLocals变量的值（即修改了父线程 ThreadLocal 中的数据），
 * 此时子线程是感知不到这个变化的。所以这里是值传递，不是引用传递。
 * @author: zhangzihao
 * @date: 10/06/2021
 **/
public class TestInheritableThreadLocal {
    public static void main(String[] args) throws Exception {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("zzh...");
        System.out.println("threadLocal.get() = " + threadLocal.get());
        // 子线程是读取不到的
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(String.format("this is sub thread name：%s, threadLocal.get(): %s", name, threadLocal.get()));
        }).start();

        Thread.sleep(1000);
        // ThreadLocal 修改为 InheritableThreadLocal
        ThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
        inheritableThreadLocal.set("inheritableThreadLocal");
        System.out.println("inheritableThreadLocal.get() = " + inheritableThreadLocal.get());
        // 在子线程中也能获取到父线程 ThreadLocal 中的数据。
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(String.format("this is sub thread name：%s, inheritableThreadLocal.get(): %s", name, inheritableThreadLocal.get()));
        }).start();
    }
}
