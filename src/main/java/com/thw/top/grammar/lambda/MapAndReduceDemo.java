package com.thw.top.grammar.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 使用lambda表达式的Map(将其应用到流中的每一个元素)和Reduce示例
 *
 * @author tianhuiwen
 * @date 2018/12/27 22:35
 */
public class MapAndReduceDemo {

    public static void main(String[] args) {
        useReduce();
    }

    /**
     * 使用lambda表达式的Reduce
     */
    public static void useReduce() {
        // 为每个订单加上12%的税
        // old way
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        // lambda
        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }

    /**
     * 使用lambda表达式的Map
     */
    public static void useMap() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);

        // 不使用lambda表达式为每个订单加上12%的税
        for (Integer cost : costBeforeTax) {
            double price = cost + 0.12 * cost;
            System.out.println(price);
        }

        // 使用lambda表达式
        costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).forEach(System.out::println);
    }

}
