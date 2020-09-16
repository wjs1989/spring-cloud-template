package com.wjs.remote;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@Configurable
@EnableFeignClients(basePackages="com.wjs.remote")
@ComponentScan("com.wjs.remote")
public class UserRemoteAutoConfiguration {

}
