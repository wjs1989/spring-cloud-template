package com.wjs.seata4.goods.controller;

import com.wjs.model.vo.BaseResult;
import com.wjs.seata4.goods.service.GoodsService;
import com.wjs.seata4.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/info")
    public BaseResult<String> getSeckillInfo() throws Exception {
        Long goodsId = goodsService.seckill(1L, 1);
        return BaseResult.success( );
    }

    @GetMapping("/info1")
    public BaseResult<String> getSeckillInfo1() throws Exception {
        goodsService.dynamic();
        return BaseResult.success( );
    }
}
