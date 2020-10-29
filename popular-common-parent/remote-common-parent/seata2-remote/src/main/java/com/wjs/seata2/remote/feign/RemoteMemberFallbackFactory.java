package com.wjs.seata2.remote.feign;

import com.wjs.domain.service.user.vo.rsp.UserInfo;
import com.wjs.model.vo.BaseResult;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 * 
 * @author wenjs
 */
@Component
public class RemoteMemberFallbackFactory implements FallbackFactory<RemoteMemberService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteMemberFallbackFactory.class);

    @Override
    public RemoteMemberService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteMemberService() {
            @Override
            public BaseResult<String> createMemberInfo(Long goodsId) {
                return null;
            }

        };
    }
    
}
