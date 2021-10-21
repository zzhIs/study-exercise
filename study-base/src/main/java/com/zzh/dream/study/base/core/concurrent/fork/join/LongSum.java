package com.zzh.dream.study.base.core.concurrent.fork.join;

import java.util.concurrent.RecursiveTask;

/**
 * fork()方法：将任务放入队列并安排异步执行，一个任务应该只调用一次fork()函数，除非已经执行完毕并重新初始化。
 * tryUnfork()方法：尝试把任务从队列中拿出单独处理，但不一定成功。
 * join()方法：等待计算完成并返回计算结果。
 * isCompletedAbnormally()方法：用于判断任务计算是否发生异常。
 */
public class LongSum extends RecursiveTask<Long> {
    //任务拆分的最小阀值
    static final int SEQUENTIAL_THRESHOLD = 1000;
    static final long NPS = (1000L * 1000 * 1000);
    static final boolean extraWork = true; // change to add more than just a sum
    int low;
    int high;
    int[] array;

    LongSum(int[] arr, int lo, int hi) {
        array = arr;
        low = lo;
        high = hi;
    }

    @Override
    protected Long compute() {
        //任务被拆分到足够小时，则开始求和
        if (high - low <= SEQUENTIAL_THRESHOLD) {
            long sum = 0;
            for (int i = low; i < high; ++i) {
                sum += array[i];
            }
            return sum;
        } else {//如果任务任然过大，则继续拆分任务，本质就是递归拆分
            int mid = low + (high - low) / 2;
            LongSum left = new LongSum(array, low, mid);
            LongSum right = new LongSum(array, mid, high);
            left.fork();
            right.fork();
            long rightAns = right.join();
            long leftAns = left.join();
            return leftAns + rightAns;
        }
    }

}
