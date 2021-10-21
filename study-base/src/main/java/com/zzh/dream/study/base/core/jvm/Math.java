package com.zzh.dream.study.base.core.jvm;

public class Math {
    private int calculate(){
        int a = 1;
        int b = 2;
        int c = (a+b)*3;
        return c;
    }

    public static void main(String args[]){
        Math math = new Math();
        math.calculate();
    }
}
