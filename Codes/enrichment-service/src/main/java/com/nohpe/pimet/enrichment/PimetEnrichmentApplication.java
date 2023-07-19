package com.nohpe.pimet.enrichment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.mybatis.spring.annotation.MapperScan;


/*

This service is to do the alarm enrichment.

Author: Gene

Date:   2023.6

 */

@SpringBootApplication
@MapperScan("com.nohpe.pimet.enrichment.mapper")
@EnableFeignClients
public class PimetEnrichmentApplication {
    public static void main(String[] args) {

        SpringApplication.run(PimetEnrichmentApplication.class, args);
    }
}