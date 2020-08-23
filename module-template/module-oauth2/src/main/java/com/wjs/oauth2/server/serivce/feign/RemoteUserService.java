package com.wjs.oauth2.server.serivce.feign;

import com.wjs.oauth2.server.domain.Result;
import com.wjs.oauth2.server.domain.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = "${spring.userinfo.server}", fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    Result<UserInfo> getUserInfo(@PathVariable("username") String username);
}
