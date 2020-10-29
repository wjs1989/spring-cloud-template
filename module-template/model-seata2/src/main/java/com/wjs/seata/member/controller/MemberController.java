package com.wjs.seata.member.controller;

import com.wjs.model.vo.BaseResult;
import com.wjs.seata.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("create")
    public BaseResult<String> createMemberInfo(Long goodsId) {

        memberService.create(goodsId);
        if (1 != 2) {
            throw new RuntimeException();
        }
        return BaseResult.success();
    }

}
