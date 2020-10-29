package com.wjs.seata4.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjs.seata4.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}
