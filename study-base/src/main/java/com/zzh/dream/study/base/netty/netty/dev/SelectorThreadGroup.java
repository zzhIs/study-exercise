package com.zzh.dream.study.base.netty.netty.dev;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectorThreadGroup {

    SelectorThread[] sts;

    ServerSocketChannel server;

    AtomicInteger xid = new AtomicInteger(0);

    //初始化的时候，指代自己
    SelectorThreadGroup stg = this;

    //在Main时，boss调用setWorker持有worker线程
    public void setWorker(SelectorThreadGroup stg) {
        this.stg = stg;
    }

    public SelectorThreadGroup(int num) {
        sts = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            sts[i] = new SelectorThread(this);
            new Thread(sts[i]).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            nextSelector(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextSelector(Channel c) {
        try {
            if (c instanceof ServerSocketChannel) {
                //在自己的boss线程中选取
                SelectorThread st = next();
                st.lbq.put(c);
                //当时ServerSocketChannel时，需要将worker传到SelectorThread中，以便接收到client，分配Selector
                st.setWorker(stg);
                st.selector.wakeup();
            } else {
                //在worker线程中选取
                SelectorThread st = nextV2();
                st.lbq.put(c);
                st.selector.wakeup();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private SelectorThread next() {
        int index = xid.incrementAndGet() % sts.length;
        return sts[index];
    }

    private SelectorThread nextV2() {
        int index = xid.incrementAndGet() % stg.sts.length;
        return sts[index];
    }
}