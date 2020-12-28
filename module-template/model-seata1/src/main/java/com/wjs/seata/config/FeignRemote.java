package com.wjs.seata.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("feignRemote")
public interface FeignRemote {
}
