package com.wjs.remote.feign;

import com.wjs.domain.service.user.vo.rsp.UserInfo;
import com.wjs.model.constant.ServerConstant;
import com.wjs.model.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 * 
 * @author wenjs
 */
@FeignClient(contextId = ServerConstant.USER_SERVER, value = ServerConstant.USER_SERVER, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    BaseResult<UserInfo> getUserInfo(@PathVariable("username") String username);
}
