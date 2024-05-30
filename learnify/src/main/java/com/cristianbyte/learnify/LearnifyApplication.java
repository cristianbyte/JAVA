package com.cristianbyte.learnify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cristianbyte.learnify")
public class LearnifyApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnifyApplication.class, args);
    }
}
