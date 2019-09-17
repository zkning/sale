package com.ning.sale;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 依赖生产端API
 */
@RequestMapping("/product")
public interface ProductService {

    @RequestMapping("/getName")
    public String getName(@RequestParam("id") Long id);
}
