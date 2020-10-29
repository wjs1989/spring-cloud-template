package com.wjs.nacos.goods.controller;

import com.wjs.model.vo.BaseResult;
import com.wjs.nacos.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/info")
    public BaseResult<String> getSeckillInfo() throws Exception {
        goodsService.scekillRemote1(1L, 1);
        return BaseResult.success();
    }

}
