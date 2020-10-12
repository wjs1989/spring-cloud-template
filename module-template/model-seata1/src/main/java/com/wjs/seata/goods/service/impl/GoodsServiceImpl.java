package com.wjs.seata.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.seata.goods.entity.Goods;
import com.wjs.seata.goods.mapper.GoodsMapper;
import com.wjs.seata.goods.service.GoodsService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenjs
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @GlobalTransactional
    @Override
    public Long seckill(Long goodsId, Integer num) {

        Goods goods = new Goods();
        goods.setTitle("我设置的");
        goodsMapper.insert(goods);

        return goods.getId();
    }
}
