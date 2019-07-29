package com.sophia.cloud;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * feignClient name
 */
@FeignClient("sophia-producer")
public interface FeignConsumer extends FeignProducer {
}
