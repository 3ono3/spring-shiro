package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author GuoJingyuan
 * @date 2019/10/10
 */
@Component
@Order(1)
public class CommandRunner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner 1...");
    }
}
