package com.ning.sale;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
@RestController
@SpringBootApplication
public class ProductApplication implements ProductService {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class);
    }

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/getPrice")
    public String getPrice() throws InterruptedException {
        Thread.sleep(2 * 1000L);
        Map<String, String> priceMap = new HashMap<>();
        priceMap.put("1", "$50");
        priceMap.put("2", "$150");
        priceMap.put("3", "$30");
        priceMap.put("4", "$220");
        priceMap.put("5", "$100");
        return "port:" + port + JSON.toJSONString(priceMap);
    }


    /**
     * feign生产服务实现
     *
     * @param id
     * @return
     */
    @Override
    public String getName(@RequestParam("id") Long id) {

        // 第三方接口调用：http://httpbin.org:80
//        try {
//            Thread.sleep(5000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://httpbin.org:80/get", String.class);
        return responseEntity.getBody();
    }
}
