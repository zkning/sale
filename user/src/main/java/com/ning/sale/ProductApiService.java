package com.ning.sale;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * 实现生产服务api
 * feignClient server name
 */
@Service
@FeignClient("product")
public interface ProductApiService extends ProductService {
}
