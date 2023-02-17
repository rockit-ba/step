package com.example.springweb.component.impl;

import com.example.springweb.component.HandleStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HandleStepComponent implements HandleStep {
    @Autowired
    private ChainStepComponent chainComponent;

    public Integer currStep = 1;
    public AtomicInteger lastFailStep = new AtomicInteger(2);

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
        System.out.println("handle MySQL 持久化 index");
    }

    public boolean isProcess() {
        return isAccess();
    }

    public String handle() {
        if (!isProcess()) {
            log.warn("handle 跳过当前步骤");
            return null;
        }
        log.info("handle 执行");
        if (System.currentTimeMillis() % 2 == 0) {
            log.error("handle {}","混沌异常");
            throw new RuntimeException("混沌异常");
        }
        return chainComponent.chain();
    }
}
