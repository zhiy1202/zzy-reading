package com.zzy;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzy.mapper")
public class ZzyReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzyReadApplication.class, args);
    }

}
