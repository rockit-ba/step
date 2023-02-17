package com.example.springweb.controller;

import com.example.springweb.component.impl.HandleStepComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private HandleStepComponent component;

    @GetMapping("/exception")
    public String exception() {
        return component.handle();
    }

}
