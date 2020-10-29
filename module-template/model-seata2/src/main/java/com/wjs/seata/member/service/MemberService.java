package com.wjs.seata.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjs.seata.member.entity.Member;

public interface MemberService  extends IService<Member> {

    Long create(Long goodsId);
}
