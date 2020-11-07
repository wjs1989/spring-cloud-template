package com.wjs.nacos.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.nacos.goods.entity.Goods;
import com.wjs.nacos.goods.mapper.GoodsMapper;
import com.wjs.nacos.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenjs
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void scekillRemote1(Long goodsId, Integer num) {
        System.out.println("+++++++++++++++++++++++++++++++++++");
        Goods goods = goodsMapper.selectById(1);
        goods.setTitle("我设置的11");
        goods.setNumber(goods.getNumber() - 1);
        goodsMapper.updateById(goods);
        System.out.println(11);
    }
}
