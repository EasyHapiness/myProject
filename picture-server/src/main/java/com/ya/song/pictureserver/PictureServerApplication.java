package com.ya.song.pictureserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = "com.ya.song.pictureserver.mapper")
public class PictureServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureServerApplication.class, args);
    }
}
