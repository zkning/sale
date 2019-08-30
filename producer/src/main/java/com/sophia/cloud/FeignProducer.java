package com.sophia.cloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 服务端API
 */
@RequestMapping("/producer")
public interface FeignProducer {

    @RequestMapping("/getName")
    public String getName(@RequestParam("name")String name);
}
