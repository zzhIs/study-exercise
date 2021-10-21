package com.zzh.dream.study.base.core.alg;

/**
 * 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变
 * 示例：
 *      输入：[1,2,3,4]
 *      返回值：[1,3,2,4]
 * @author: zhangzihao
 * @date: 09/06/2021
 **/
public class TestArraySort {

    public static void main(String[] args) {
        int[] arg = {1,2,0,4,6,8,3,4};

        for (int i : sort(arg)){
            System.out.println(i);
        }
    }

    private static int[] sort(int[] array){
        if (null == array || array.length == 0) {
            return array;
        }

        int[] temp = new int[array.length];
        int index = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                temp[index] = array[i];
                index ++;
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                temp[index] = array[i];
                index ++;
            }
        }
        return temp;
    }

}
