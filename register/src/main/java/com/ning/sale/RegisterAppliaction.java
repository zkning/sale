package com.ning.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterAppliaction {
    public static void main(String[] args) {
        SpringApplication.run(RegisterAppliaction.class, args);
    }
}
