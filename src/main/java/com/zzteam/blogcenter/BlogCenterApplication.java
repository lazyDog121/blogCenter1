package com.zzteam.blogcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzteam.blogcenter.Mapper")
public class BlogCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCenterApplication.class, args);
    }

}
