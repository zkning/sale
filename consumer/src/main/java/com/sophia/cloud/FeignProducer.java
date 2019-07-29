package com.sophia.cloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/producer")
public interface FeignProducer {

    @RequestMapping("/getName")
    public String getName(@RequestParam("name") String name);
}
