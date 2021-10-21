package com.zzh.dream.study.base.core.concurrent.aqs;

import java.util.concurrent.Semaphore;

/**
 * Semaphore测试案例
 * @since 2021-03-03
 * @author zhangzihao
 */
public class SemaphoreTest {


        public static void main(String[] args) {
            Semaphore semaphore = new Semaphore(2);
            for (int i=0;i<5;i++){
                new Thread(new Task(semaphore,"thread+"+i)).start();
            }
        }

        static class Task extends Thread{
            Semaphore semaphore;

            public Task(Semaphore semaphore,String tname){
                this.semaphore = semaphore;
                this.setName(tname);
            }

            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+":aquire() at time:"+System.currentTimeMillis());
                    Thread.sleep(1000);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+":release() at time:"+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
}
