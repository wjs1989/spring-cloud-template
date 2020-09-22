package com.wjs.seckill.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.model.util.BeanUtils;
import com.wjs.model.util.IdGeneratorUtil;
import com.wjs.model.util.MapUtils;
import com.wjs.seckill.core.costant.SeckillCostant;
import com.wjs.seckill.goods.dto.GoodsRedisDTO;
import com.wjs.seckill.goods.entity.Goods;
import com.wjs.seckill.goods.entity.GoodsOrder;
import com.wjs.seckill.goods.mapper.GoodsMapper;
import com.wjs.seckill.goods.mapper.GoodsOrderMapper;
import com.wjs.seckill.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wenjs
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Override
    public Goods querySeckillGoodsById(Long id) throws Exception {

        String key = SeckillCostant.SECKILL_REIDS_KEY_PREFIX + id;
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        if (entries != null && !entries.isEmpty()) {
            return MapUtils.mapToObject(entries, Goods.class);
        }

        Goods goods = baseMapper.selectById(id);
        redisTemplate.opsForHash().putAll(key, MapUtils.convertToMap(goods));
        redisTemplate.expire(key, 10, TimeUnit.SECONDS);
        return goods;
    }

    @Override
    public List<Goods> querySeckillGoods() {

        List<Goods> goodsList = baseMapper.selectList(null);
        if (goodsList != null && !goodsList.isEmpty()) {
            goodsList.forEach(g -> {
                GoodsRedisDTO grd = new GoodsRedisDTO();
                BeanUtils.copyProperties(g, grd);
                String redisKey = SeckillCostant.SECKILL_REIDS_KEY_PREFIX + g.getId();
                redisTemplate.opsForHash().putAll(redisKey, MapUtils.convertToMap(grd));
            });
        }

        return goodsList;
    }

    @Override
    public boolean seckill(Long userId, Long goodsId, Integer num) {
        if (userId == null || goodsId == null ||
                num == null || num < 0) {
            return false;
        }

        //判断redis 中保存商品的库存够不够
        Long sku = seckill(goodsId, num);
        if (sku < 0) {
            return false;
        }

        int chance = 300;
        boolean seckillReslut = false;
        do {
            Goods goods = baseMapper.selectById(goodsId);
            if (goods == null) {
                return false;
            }
            goods.setNumber(goods.getNumber() - num);

            GoodsOrder goodsOrder = new GoodsOrder();
            goodsOrder.setId(IdGeneratorUtil.nextId());
            goodsOrder.setUserId(userId);
            goodsOrder.setExpressNo(String.valueOf(IdGeneratorUtil.nextId()));
            // 秒杀成功，生成订单，扣除库存
            seckillReslut = transactionTemplate.execute(transactionStatus -> {
                int actGoods = baseMapper.updateById(goods);
                if (actGoods < 1) {
                    return false;
                }
                int actOrder = goodsOrderMapper.insert(goodsOrder);
                if (actOrder < 1) {
                    return false;
                }
                return true;
            });
        } while (!seckillReslut && chance-- > 0);
        return seckillReslut;
    }

    @Cacheable(value="goods",key="#id")
    @Override
    public Goods cache(Long id) throws Exception {
        Goods goods = baseMapper.selectById(id);
        return goods;
    }

    private Long seckill(Long goodsId, Integer num) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(SeckillCostant.SECKILL_LUA)));

        List<Object> keyList = new ArrayList();
        keyList.add(SeckillCostant.SECKILL_REIDS_KEY_PREFIX + goodsId);
        keyList.add(SeckillCostant.SECKILL_SKU_FIELD);
        System.out.println(redisScript.getScriptAsString());

        Long sku = redisTemplate.execute(redisScript, keyList, num);
        return sku;
    }
}
