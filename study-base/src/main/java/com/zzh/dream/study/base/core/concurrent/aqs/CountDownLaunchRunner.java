package com.zzh.dream.study.base.core.concurrent.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch测试案例
 * @since 2021-03-03
 * @author zhangzihao
 */
public class CountDownLaunchRunner {

    /**
     * 看大夫任务
     */
    public static class SeeDoctorTask implements Runnable {
        private CountDownLatch countDownLatch;

        public SeeDoctorTask(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            try {
                System.out.println("开始看医生");
                Thread.sleep(3000);
                System.out.println("看医生结束，准备离开病房");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (countDownLatch != null)
                    countDownLatch.countDown();
            }
        }

    }

    /**
     * 排队交钱的任务
     */
    public static class QueueTask implements Runnable {

        private CountDownLatch countDownLatch;

        public QueueTask(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }
        public void run() {
            try {
                System.out.println("开始在医院药房排队买药....");
                Thread.sleep(5000);
                System.out.println("排队成功，可以开始缴费买药");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (countDownLatch != null)
                    countDownLatch.countDown();
            }
        }
    }

/**
 * 媳妇去看病，轮到媳妇看大夫时
 * 我就开始去排队准备交钱了。
 */

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new SeeDoctorTask(countDownLatch)).start();
        new Thread(new QueueTask(countDownLatch)).start();
        //等待线程池中的2个任务执行完毕，否则一直
        countDownLatch.await();
        System.out.println("over，回家 cost:"+(System.currentTimeMillis()-now));
    }
}
