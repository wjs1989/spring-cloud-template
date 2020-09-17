package com.wjs.seckill.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.model.util.MapUtils;
import com.wjs.seckill.goods.entity.Goods;
import com.wjs.seckill.goods.mapper.GoodsMapper;
import com.wjs.seckill.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Goods querySeckillGoodsById(Long id) throws Exception {

        String key = "SeckillGoodsId:" + id;
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        if (entries != null && !entries.isEmpty()) {
            return MapUtils.mapToObject(entries, Goods.class);
        }

        Goods goods = baseMapper.selectById(id);
        redisTemplate.opsForHash().putAll(key,MapUtils.convertToMap(goods));
        redisTemplate.expire(key, 10, TimeUnit.SECONDS);
        return goods;
    }

    @Override
    public List<Goods> querySeckillGoods() {

        String key = "SeckillGoodsId:";
        //String key = "SeckillGoods:All";
       // Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        //if (entries != null && !entries.isEmpty()) {
             //return entries.values();
        //}

        List<Goods> goodsList = baseMapper.selectList(null);
        if(goodsList != null && !goodsList.isEmpty()){
            goodsList.forEach(g->{
                redisTemplate.opsForHash().putAll(key+g.getId(),MapUtils.convertToMap(g));
                redisTemplate.expire(key, 10, TimeUnit.SECONDS);
            });
        }
        //Map<Long, List<Goods>> collect = goodsList.stream().collect(Collectors.groupingBy(Goods::getId));
        //redisTemplate.opsForHash().putAll(key,MapUtils.convertToMapAbsolute(collect));

        return goodsList;
    }
}
