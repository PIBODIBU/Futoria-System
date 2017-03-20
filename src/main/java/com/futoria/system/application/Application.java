package com.futoria.system.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.futoria.system.model", "com.futoria.core.model"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}