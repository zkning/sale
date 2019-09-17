package com.ning.sale;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductPriceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @Value("${productApi.getPrice}")
    String productUrl;

    @Value("${productApi.getName}")
    String getNameUrl;


    public String getPrice() {
        logger.info("[getPrice]调用查询商品价格");
        return restTemplate.getForObject(productUrl, String.class);
    }

    //    @CacheResult
    @HystrixCollapser(batchMethod = "getNames", collapserProperties = {@HystrixProperty(name = HystrixPropertiesManager.TIMER_DELAY_IN_MILLISECONDS, value = "3000")})
//    @HystrixCommand(fallbackMethod = "getNameFallback")
    public String getName(@CacheKey Integer id) {
        logger.info("[getPrice]调用查询商品名称");
        String rst = restTemplate.getForObject(getNameUrl + "?id=" + id, String.class);
        logger.info(rst);
        return rst;
    }

    @HystrixCommand(fallbackMethod = "getNamesFallback")
    public List<String> getNames(List<Integer> id) {
        logger.info("[getPrice]调用查询商品名称");
        List<String> list = new ArrayList<>();
        list.add("调用商品查询请求合并");
        return list;
    }

    public String getNameFallback(Integer id) {
        return "getNameFallback";
    }

    public List<String> getNamesFallback(List<Integer> id) {
        return new ArrayList<>();
    }
}
