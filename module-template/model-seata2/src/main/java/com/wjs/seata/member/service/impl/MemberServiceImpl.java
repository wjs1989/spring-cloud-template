package com.wjs.seata.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.seata.member.entity.Member;
import com.wjs.seata.member.mapper.MemberMapper;
import com.wjs.seata.member.service.MemberService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    //@GlobalTransactional
    @Override
    public Long create(Long goodsId) {

        Member member = new Member();
        member.setGoodsId(goodsId);

        String xid = RootContext.getXID();
        member.setName(xid);
        baseMapper.insert(member);

        return member.getId();
    }
}
