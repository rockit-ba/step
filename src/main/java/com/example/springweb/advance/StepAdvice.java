package com.example.springweb.advance;

import com.example.springweb.component.ChainStep;
import com.example.springweb.component.HandleStep;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StepAdvice implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @ExceptionHandler(RuntimeException.class)
    public void stepExceptionPersist(Exception e) throws Exception {
        StackTraceElement sourceE = e.getStackTrace()[0];
        Object stepBean = applicationContext.getBean(Class.forName(sourceE.getClassName()));
        if (stepBean instanceof HandleStep handleStep) {
            handleStep.persistence();
            throw e;
        }
        if (stepBean instanceof ChainStep chainStep) {
            chainStep.persistence();
            throw e;
        }
        throw e;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
