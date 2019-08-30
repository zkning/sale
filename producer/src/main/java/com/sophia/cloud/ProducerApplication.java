package com.sophia.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/get")
    public String get() throws InterruptedException {
        Thread.sleep(1 * 1000L);
        System.out.println("ProducerApplication get");
        return ">>>>>>>>>>>>>>>>>> ProducerApplication get";
    }


    /**
     * feign生产服务实现
     *
     * @param name
     * @return
     */
    @Override
    public String getName(@RequestParam("name") String name) {

        // 第三方接口调用：http://httpbin.org:80
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://httpbin.org:80/get", String.class);
        return "由Feign producer生产的数据: " + responseEntity.getBody();
    }
}
