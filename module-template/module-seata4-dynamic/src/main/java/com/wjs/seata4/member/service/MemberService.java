package com.wjs.seata4.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjs.seata4.member.entity.Member;

public interface MemberService  extends IService<Member> {

    Long insert(Member member);
}
