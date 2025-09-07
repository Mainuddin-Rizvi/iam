package com.example.iam.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** Main entry point for IAM Gateway */
@SpringBootApplication(scanBasePackages = "com.example.iam")
@EnableJpaRepositories(basePackages = "com.example.iam.domain.repository")
@EntityScan(basePackages = "com.example.iam.domain.entity")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
