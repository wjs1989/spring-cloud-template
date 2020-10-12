package com.wjs.seata.goods.controller;

import com.wjs.model.vo.BaseResult;
import com.wjs.seata.goods.service.GoodsService;
import io.seata.core.context.RootContext;
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
    public BaseResult<String> getSeckillInfo() throws Exception{

        goodsService.seckill(1L,1);

        String xid = RootContext.getXID();
        return BaseResult.success(xid);
    }



}
