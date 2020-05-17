package com.example.demo.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(98)
@Component
public class CustomRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("this is customrunner...");
    }
}
