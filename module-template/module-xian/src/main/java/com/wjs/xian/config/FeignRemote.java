package com.wjs.xian.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("feignRemote")
public interface FeignRemote {
}
