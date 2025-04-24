package com.example.mps;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pengYuJun
 */
@MapperScan("com.example.mps.mapper")
@SpringBootApplication
@EnableDubbo
public class MultiProSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiProSystemApplication.class, args);
    }

}
