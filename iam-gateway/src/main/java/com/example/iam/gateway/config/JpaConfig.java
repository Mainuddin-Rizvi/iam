package com.example.iam.gateway.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.iam.domain.repository")
@EntityScan(basePackages = "com.example.iam.domain.entity")
public class JpaConfig {
}
