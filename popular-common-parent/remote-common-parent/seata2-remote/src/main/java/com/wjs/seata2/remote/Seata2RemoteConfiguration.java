package com.wjs.seata2.remote;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@Configurable
@EnableFeignClients
@ComponentScan
public class Seata2RemoteConfiguration {

}
