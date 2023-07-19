package com.nohpe.pimet.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*

This service is pimet core functionality.

Author: Gene

Date:   2023.5

 */
@SpringBootApplication
@MapperScan("com.nohpe.pimet.core.mapper")
@EnableFeignClients
public class PimetCoreApplication {
    public static void main(String[] args) {

        SpringApplication.run(PimetCoreApplication.class, args);
    }
}