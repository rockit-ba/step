package com.example.springweb.component;

import java.util.concurrent.atomic.AtomicInteger;


public interface Step {
    /**
     * 当前步数，无状态
     */
    Integer currStep();

    /**
     * 最后失败的步数，有状态需要从持久化的地方获取
     */
    AtomicInteger lastFailStep();

    /**
     * 持久化步数信息
     */
    void persistence();

    /**
     * 当前步数是否可以执行
     */
    default boolean isAccess() {
        boolean access = currStep() >= lastFailStep().get();
        if (access) {
            lastFailStepIncrease();
        }
        return access;
    };
    default void lastFailStepIncrease() {
        System.out.println("步数自增："+lastFailStep().incrementAndGet());
    }
}
