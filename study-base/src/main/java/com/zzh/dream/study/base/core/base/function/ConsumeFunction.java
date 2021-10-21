package com.zzh.dream.study.base.core.base.function;

import java.util.function.Function;

/**
 * @description: 要消费一个函数，消费函数需要在参数列表正确地描述函数类型
 * @author: zhangzihao
 * @date: 08/07/2021
 **/
public class ConsumeFunction {

    public static Two consume(Function<One,Two> oneTwo){
        return oneTwo.apply(new One());
    }

    public static void main(String[] args) {

        Two two = consume(new Function<One, Two>() {
            @Override
            public Two apply(One one) {
                return new Two();
            }
        });

        Two two1 = consume(one -> new Two());
    }
}

class One {}
class Two {}
