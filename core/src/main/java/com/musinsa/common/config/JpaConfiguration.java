package com.musinsa.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.musinsa.domain.*.repository"})
public class JpaConfiguration {

}