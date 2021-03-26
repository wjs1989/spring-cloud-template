package com.wjs.seata.member.controller;

import com.wjs.seata.member.entity.Member;
import com.wjs.seata.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    

    @GetMapping("1")
    public Long insert(){
        Member m = new Member();
        m.setName("1234");
        return  memberService.insert(m);
    }

    @GetMapping("2")
    public Member get(){

        return  memberService.getById(1);
    }
}
