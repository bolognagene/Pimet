package com.nohpe.pimet.fronted;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*

This service is pimet fronted service, all actions from GUI will be handed first by this service.
If the action is GET request, then fronted-service will directly query from DB
but if the actions is POST request, then fronted-service will send this request to pimet-core to execute.

Author: Gene

Date:   2023.6

 */
@SpringBootApplication
@MapperScan("com.nohpe.pimet.fronted.mapper")
@EnableFeignClients
public class PimetFrontedApplication {
    public static void main(String[] args) {

        SpringApplication.run(PimetFrontedApplication.class, args);
    }
}