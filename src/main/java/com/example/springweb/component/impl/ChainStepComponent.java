package com.example.springweb.component.impl;

import com.example.springweb.component.ChainStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author pan
 * @date 2023/2/17 19:23
 */
@Component
@Slf4j
public class ChainStepComponent implements ChainStep {
    public AtomicInteger lastFailStep = new AtomicInteger(3);
    public Integer currStep = 2;

    @Override
    public Integer currStep() {
        return currStep;
    }

    @Override
    public AtomicInteger lastFailStep() {
        return lastFailStep;
    }

    @Override
    public void persistence() {
        System.out.println("chain redis 持久化");
    }
    public boolean isProcess() {
        return isAccess();
    }
    public String chain() {
        if (!isProcess()) {
            log.warn("chain 跳过当前步骤");
            return null;
        }
        log.info("chain 执行");
        if (System.currentTimeMillis() % 2 == 0) {
            log.error("chain {}","混沌异常");
            throw new RuntimeException("混沌异常");
        }
        return "ok";
    }
}
