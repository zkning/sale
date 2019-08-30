package com.sophia.cloud;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 依赖生产端API
 */
@RequestMapping("/producer")
public interface FeignProducer {

    @RequestMapping("/getName")
    public String getName(@RequestParam("name") String name);
}
