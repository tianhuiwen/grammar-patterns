package com.thw.top.patterns.design.strategy.demo;

/**
 * @author tianhuiwen
 * @description 使用策略模式:针对一组算法，将每一个算法封装到具有共同接口的独立的类
 * @date 2018/11/17 12:08
 */
public class UseStrategy {

    public static void main(String[] args) {
        // 具体使用策略
        Fruit apple = new Apple();
        // 将策略放入环境中并执行策略
        new FruitPrice(apple).printFruitPrice();
    }
}
