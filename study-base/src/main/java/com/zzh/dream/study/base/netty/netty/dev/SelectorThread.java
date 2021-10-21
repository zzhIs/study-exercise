package com.zzh.dream.study.base.netty.netty.dev;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

public class SelectorThread extends ThreadLocal<LinkedBlockingDeque<Channel>> implements Runnable {

    Selector selector = null;

    LinkedBlockingDeque<Channel> lbq = get();

    SelectorThreadGroup stg;

    @Override
    protected LinkedBlockingDeque<Channel> initialValue() {

        return new LinkedBlockingDeque<>();
    }

    SelectorThread(SelectorThreadGroup stg) {
        try {
            this.stg = stg;
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //阻塞
                int nums = selector.select();
                if (nums > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    if (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        } else if (key.isWritable()) {

                        }
                    }
                }
                if (!lbq.isEmpty()) {
                    Channel c = lbq.take();
                    if (c instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) c;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName() + " register listen");
                    } else if (c instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) c;
                        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
                        client.register(selector, SelectionKey.OP_READ, byteBuffer);
                        System.out.println(Thread.currentThread().getName() + " register client:" + client.getRemoteAddress());

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void readHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + " read......");
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        while (true) {
            try {
                int num = client.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (num == 0) {
                    break;
                } else if (num < 0) {
                    System.out.println("client:" + client.getRemoteAddress() + " is closed.....");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + " acceptHandler....");
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            stg.nextSelector(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWorker(SelectorThreadGroup stgWorker) {
        this.stg = stgWorker;
    }
}