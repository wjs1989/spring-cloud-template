package com.wjs.seckill.goods.controller;

import com.wjs.domain.service.seckill.vo.rsp.GoodsRspVo;
import com.wjs.model.vo.BaseResult;
import com.wjs.seckill.goods.entity.Goods;
import com.wjs.seckill.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;


    @GetMapping("/seckill")
    public BaseResult<GoodsRspVo> getSeckillInfo() throws Exception{

        Goods goods = goodsService.querySeckillGoodsById(1L);
        GoodsRspVo vo = new GoodsRspVo();
        BeanUtils.copyProperties(goods,vo);
        return BaseResult.success(vo);
    }

    @GetMapping("/seckillAll")
    public BaseResult<GoodsRspVo> getAllSeckillInfo() throws Exception{

        List<Goods> goodsList = goodsService.querySeckillGoods();
        GoodsRspVo vo = new GoodsRspVo();
        BeanUtils.copyProperties(goodsList.get(0),vo);
        return BaseResult.success(vo);
    }
}
