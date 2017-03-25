package com.futoria.system.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = {"com.futoria.system.model", "com.futoria.core.model"})
@EnableJpaRepositories(basePackages = {"com.futoria.system.repository", "com.futoria.core.repository"},
        transactionManagerRef = "txManager")
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}