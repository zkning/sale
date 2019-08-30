package com.sophia.cloud;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实现生产端API
 * feignClient server name
 */
@FeignClient("sophia-producer")
public interface FeignConsumer extends FeignProducer {
}
