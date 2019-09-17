package com.ning.sale;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@EnableFeignClients
@RequestMapping("/user")
@SpringBootApplication
public class UserApplication {

    @Autowired
    ProductApiService productApiService;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Value("${productApi.getPrice}")
    String productUrl;

    @RequestMapping("/info")
    public String get() throws InterruptedException {
        Map<String, String> cartMap = new HashMap<>();
        cartMap.put("name", "张三");
        cartMap.put("mobile", "131890310008");
        return JSON.toJSONString(cartMap);
    }

    @RequestMapping("/getProductName")
    public String getProductName() throws InterruptedException {
        return productApiService.getName(1L);
    }


    @RequestMapping("/getPrice")
    public String getPrice() {
        return restTemplate.getForObject(productUrl, String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
