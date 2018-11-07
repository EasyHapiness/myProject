package com.ya.song.eurakeregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurakeRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurakeRegisterApplication.class, args);
    }
}
