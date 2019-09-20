package com.ning.sale;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.ning.sale.event.MessageEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
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
@EnableHystrix
@RefreshScope
public class UserApplication {

    @Autowired
    ProductApiService productApiService;
    @Autowired
    ProductPriceService productPriceService;

    @Value("${version}")
    String version;

    @Value("${saleKey}")
    String saleKey;

    @Autowired
    MessageEventPublisher messageEventPublisher;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

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


    @RequestMapping("/ver")
    public String getVersion() {
        return version + " >>> " + saleKey;
    }

    @RequestMapping("/push")
    public void push() {
        messageEventPublisher.send("发送消息事件");
    }


    @RequestMapping("/getPrice")
    public String getPrice() {
        HystrixRequestContext.initializeContext();  //初始化请求上下文
        productPriceService.getName(1);
        productPriceService.getName(1);
        return productPriceService.getName(1);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
