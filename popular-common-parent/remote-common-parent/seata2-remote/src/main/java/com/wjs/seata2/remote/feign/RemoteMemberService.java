package com.wjs.seata2.remote.feign;

import com.wjs.domain.service.user.vo.rsp.UserInfo;
import com.wjs.model.constant.ServerConstant;
import com.wjs.model.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务
 * 
 * @author wenjs
 */
@FeignClient(contextId = ServerConstant.SEATA2_SERVER, value = ServerConstant.SEATA2_SERVER, fallbackFactory = RemoteMemberFallbackFactory.class)
public interface RemoteMemberService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @PostMapping(value = "/member/create")
    BaseResult<String> createMemberInfo(@RequestParam Long goodsId);
}
