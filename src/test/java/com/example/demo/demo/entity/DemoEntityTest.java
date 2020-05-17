package com.example.demo.demo.entity;

import com.example.demo.common.DemoConfig;
import com.example.demo.common.SpringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DemoEntityTest {

    @Autowired
    private DemoConfig demoConfig;

    @Test
    void name() {
        System.out.println(demoConfig.getAge());
    }

    @Test
    void test1() {
        ApplicationContext context = SpringUtils.getApplicationContext();

        System.out.println(1);
    }
}
