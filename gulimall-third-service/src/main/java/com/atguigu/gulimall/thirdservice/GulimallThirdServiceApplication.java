package com.atguigu.gulimall.thirdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GulimallThirdServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallThirdServiceApplication.class, args);
    }

}
