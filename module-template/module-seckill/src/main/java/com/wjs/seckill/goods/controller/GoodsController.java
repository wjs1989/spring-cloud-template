package com.wjs.seckill.goods.controller;

import com.wjs.domain.service.seckill.vo.req.SeckillReq;
import com.wjs.domain.service.seckill.vo.rsp.GoodsRspVo;
import com.wjs.ehcache.util.EhcacheUtil;
import com.wjs.model.util.BeanUtils;
import com.wjs.model.vo.BaseResult;
import com.wjs.redis.config.RedisKeyEnum;
import com.wjs.redis.model.*;
import com.wjs.redis.service.RedisService;
import com.wjs.seckill.goods.entity.Goods;
import com.wjs.seckill.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        SeckillReq seckill = new SeckillReq();
        seckill.setGoodsId(111L);
        BaseRedis request = new StringRedis("aaa",seckill);
        //request.setRedisKey(RedisKeyEnum.DISTRICT_CODE,"你好");
        redisService.save(request);
       // RedisResult redisResult = redisService.get(RedisKeyEnum.DISTRICT_CODE);

        RedisResult aaa = redisService.get("aaa");
        redisService.delete("aa");

        HashRedis redis = (HashRedis)BaseRedis.createRedis(RedisKeyEnum.DISTRICT_HASH, seckill);
        redis.put("num",18);
        redis.put("name","name");
        redis.put("age",18);
        redisService.save(redis);
        RedisResult redisResult = redisService.get(RedisKeyEnum.DISTRICT_HASH);


        ListRedis listRedis = new ListRedis();
        listRedis.setKey("mylist");
        listRedis.setValue("li");
        redisService.save(listRedis);

        listRedis.setValue(Arrays.asList("wen","liu","huang"));
        redisService.save(listRedis);
        RedisResult r =  redisService.get("mylist");

        MemberRedis memberRedis = new MemberRedis("myMember",Arrays.asList("wen","liu","huang"));
        redisService.save(memberRedis);
        RedisResult m =  redisService.get("myMember");

        return BaseResult.success(m.getMemberRedis().getValue());
    }

    @GetMapping("/cache")
    public BaseResult<String> cache(Long id) throws Exception{
        return BaseResult.success(goodsService.cache(id));
    }

    @GetMapping("/ehcache")
    public BaseResult<String> ehcache(Long id) throws Exception{
        EhcacheUtil instance = EhcacheUtil.getInstance();
        SeckillReq seckill = new SeckillReq();
        seckill.setGoodsId(111L);
        instance.put("q","123");
        instance.put("q1",seckill);
        SeckillReq q =  (SeckillReq)instance.get("q1");

        return BaseResult.success(q.getGoodsId());
    }
}
