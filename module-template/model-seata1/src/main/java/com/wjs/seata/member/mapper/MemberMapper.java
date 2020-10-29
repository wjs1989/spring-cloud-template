package com.wjs.seata.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjs.seata.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}
