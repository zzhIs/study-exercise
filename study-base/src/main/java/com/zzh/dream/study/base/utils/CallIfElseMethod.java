package com.zzh.dream.study.base.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用工厂+策略消除if—else
 */
public class CallIfElseMethod {

    public interface Fruit {
        void eat();
    }

    public static class Apple implements Fruit {
        @Override
        public void eat() {
            System.out.println("是苹果，可以直接吃");
        }
    }

    public static class Banana implements Fruit {
        @Override
        public void eat() {
            System.out.println("吃香蕉了，需要先剥下皮");
        }
    }

    /**
     * 工厂方法
     */
    public static class FruitFactory {
        private static Map<String, Fruit> fruitMap = new HashMap<>();

        static {
            fruitMap.put("apple", new Apple());
            fruitMap.put("banana", new Banana());
        }

        public static Fruit getFruit(String fruitType) {
            return fruitMap.get(fruitType);
        }
    }

    /**
     * 也可以使用反射--取代工厂方法
     */
    public class FruitFactory2 {
        public  <T extends Fruit> T getFruit(Class<T> clz) throws Exception {
            return clz.newInstance();
        }
    }

    public static void main(String[] args) {
//        Fruit fruit = FruitFactory.getFruit("");
//        fruit.eat();
        if ("x1231231xX".contains("x")) {
            System.out.println("。。。");
        }


        System.out.println("x1231231xX".replaceAll("x","X"));
    }

}
