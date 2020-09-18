package com.wjs.seckill.goods.controller;

import com.wjs.domain.service.seckill.vo.req.SeckillReq;
import com.wjs.domain.service.seckill.vo.rsp.GoodsRspVo;
import com.wjs.model.util.BeanUtils;
import com.wjs.model.vo.BaseResult;
import com.wjs.seckill.config.redis.config.RedisKeyEnum;
import com.wjs.seckill.config.redis.model.BaseRedis;
import com.wjs.seckill.config.redis.service.RedisService;
import com.wjs.seckill.goods.entity.Goods;
import com.wjs.seckill.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @GetMapping("/info")
    public BaseResult<GoodsRspVo> getSeckillInfo() throws Exception{

        Goods goods = goodsService.querySeckillGoodsById(1L);
        GoodsRspVo vo = new GoodsRspVo();
        BeanUtils.copyProperties(goods,vo);
        return BaseResult.success(vo);
    }

    @GetMapping("/infos")
    public BaseResult<List<GoodsRspVo>> getAllSeckillInfo() throws Exception{

        List<Goods> goodsList = goodsService.querySeckillGoods();
        List<GoodsRspVo> vo = BeanUtils.batchTransform(GoodsRspVo.class, goodsList);
        return BaseResult.success(vo);
    }

    @PostMapping("/seckill")
    public BaseResult<String> seckill(@RequestBody SeckillReq seckill) throws Exception{
        boolean result = goodsService.seckill(seckill.getUserId(), seckill.getGoodsId(), seckill.getNum());
        return BaseResult.success(result);
    }

    @GetMapping("/redis")
    public BaseResult<String> redisService() throws Exception{
        BaseRedis request = new BaseRedis();
        request.setRedisKey(RedisKeyEnum.DISTRICT_CODE,"你好");
        redisService.save(request);

       return BaseResult.success();
    }
}
