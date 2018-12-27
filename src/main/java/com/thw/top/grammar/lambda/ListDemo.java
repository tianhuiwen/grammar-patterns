package com.thw.top.grammar.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author tianhuiwen
 * @date 2018/12/27 21:43
 */
public class ListDemo {

    public static void main(String[] args) {
        forEachList();
    }

    /**
     * 开启线程（演示语法，实际项目中不要显式创建线程，请使用线程池）
     */
    private void startThread() {
        // old way
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }).start();

        // lambda
        new Thread(() -> System.out.println("hello world")).start();
    }

    /**
     * 遍历集合
     */
    private static void forEachList() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        // old way
        for (Integer integer : integers) {
            System.out.println(integer);
        }

        // lambda
        integers.forEach(integer -> System.out.println(integer));

        // 使用双冒号操作符 :: (目标引用::方法)
        integers.forEach(System.out::println);
    }

}
