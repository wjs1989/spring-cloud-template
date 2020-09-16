/**
 * @ClassName package-info
 * @Description: TODO
 * @Author wjs
 * @Date 2020/8/21
 * @Version V1.0
 **/
package com.wjs.oauth2;



/**
 * 此资源的使用方法
 * 1、在pom中添加此包
 * 2、在启动类上加入注解 @EnableCustomConfig
 * 3、配置yml文件


 security:
     oauth2:
         client:
             client-secret: 123456
             clientId: pc
             grant-type: client_credentials
             scope: all
             access-token-uri: http://isky-oauth2-server/oauth/token

     resource:
         prefer-token-info: false
         loadBalanced: true
         token-info-uri: http://isky-oauth2-server/oauth/check_token
     ignore: # 忽略校验的地址
         urls:
         - /v2/api-docs
         - /actuator/**
         - /user/info/*

 *
 **/