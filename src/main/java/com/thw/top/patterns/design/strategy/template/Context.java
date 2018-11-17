package com.thw.top.patterns.design.strategy.template;

/**
 * 设计模式-策略模式
 * 环境(Context)角色：持有一个Strategy的引用
 * 抽象策略(Strategy)角色：这是一个抽象角色，通常由一个接口或抽象类实现
 * 具体策略(ConcreteStrategy)角色：包装了相关的算法或行为
 *
 * @author tianhuiwen
 * @description 环境角色
 * @date 2018-11-17
 */
public class Context {

    /**
     * 策略对象
     */
    private Strategy strategy;

    /**
     * @param strategy 具体策略对象
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @description 执行策略方法
     * @date 2018年1月14日 下午8:43:31
     */
    public void contextInterface() {
        strategy.strategyInterface();
    }
}
