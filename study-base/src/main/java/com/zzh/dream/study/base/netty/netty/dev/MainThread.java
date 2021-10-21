package com.zzh.dream.study.base.netty.netty.dev;

/**
 * MainThread:这个类负责程序入口，不做任何的业务
 * <p>
 * SelectorThreadGroup：如上图，会有多个Selector,故用这个类管理
 * <p>
 * SelectorThread:核心类，用来做Selector
 */
public class MainThread {
    public static void main(String[] args) {
        //创建多个boss线程
        SelectorThreadGroup boss = new SelectorThreadGroup(3);
        //创建多个worker线程
        SelectorThreadGroup worker = new SelectorThreadGroup(3);
        //boss持有worker，因为当接收到客户端时，需要将客户端分配给worker线程
        boss.setWorker(worker);
        boss.bind(6666);
        boss.bind(7777);
        boss.bind(8888);
    }
}