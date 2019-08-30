package com.sophia.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
@RequestMapping("/consumer")
@SpringBootApplication
public class ConsumerApplication {

    @Autowired
    FeignConsumer feignConsumer;

    @RequestMapping("/get")
    public String get() throws InterruptedException {
        Thread.sleep(1 * 1000L);
        System.out.println("Consumer get");
        return ">>>>>>>>>>>>>>>>>> Consumer get";
    }

    @RequestMapping("/index")
    public String index() throws InterruptedException {
        System.out.println("Consumer main page");
        return ">>>>>>>>>>>>>>>>>> Consumer main page";
    }

    @RequestMapping("/getName")
    public String getName(@RequestParam("name") String name) {
        return feignConsumer.getName(name);
    }
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
