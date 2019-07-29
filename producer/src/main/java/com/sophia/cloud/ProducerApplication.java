package com.sophia.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@RestController
@SpringBootApplication
public class ProducerApplication implements FeignProducer {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }

    @Autowired
    FeignProducer feignProducer;

    @RequestMapping("/get")
    public String get() throws InterruptedException {
        Thread.sleep(1 * 1000L);
        System.out.println("ProducerApplication get");
        return ">>>>>>>>>>>>>>>>>> ProducerApplication get";
    }


    /**
     * feign生产服务
     *
     * @param name
     * @return
     */
    @Override
    public String getName(@RequestParam("name") String name) {
        return "由Feign producer生产的数据: " + name;
    }
}
