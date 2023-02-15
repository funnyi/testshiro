package com.study.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.study.boot.mapper")
@SpringBootApplication
public class TestshiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestshiroApplication.class, args);
    }

}
