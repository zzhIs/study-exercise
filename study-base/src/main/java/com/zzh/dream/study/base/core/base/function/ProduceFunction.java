package com.zzh.dream.study.base.core.base.function;

/**
 * @description: 高阶函数（Higher-order Function）只是一个消费或产生函数的函数
 * @author: zhangzihao
 * @date: 08/07/2021
 **/
public class ProduceFunction {
    /**
     * 使用 Lambda 表达式，可以轻松地在方法中创建和返回一个函数。
     */
    public static FunSS produce(){
        return s -> s.toLowerCase();
    }

    /**
     * 本质是内部类
     * @return
     */
    public static FunSS produce1(){
        return new FunSS() {
            @Override
            public String apply(String s) {
                return s.toLowerCase();
            }
        };
    }

    public static void main(String[] args) {
        System.out.println(produce().apply("HELLO WORD..."));
    }
}
