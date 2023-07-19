package com.nohpe.pimet.tt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

This service is to pimet trouble ticket service, to implement whole tt lifecycle.

Author: Gene

Date:   2023.6

 */
@SpringBootApplication
@MapperScan("com.nohpe.pimet.tt.mapper")
public class PimetTTServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(PimetTTServiceApplication.class, args);
    }
}