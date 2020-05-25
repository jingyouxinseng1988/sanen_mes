package com.plc.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.plc.platform.dao")
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
