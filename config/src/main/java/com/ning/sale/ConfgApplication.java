package com.ning.sale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 */
@EnableConfigServer
@SpringBootApplication
public class ConfgApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfgApplication.class, args);
    }
}
