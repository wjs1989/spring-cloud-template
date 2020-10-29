package com.wjs.seckill.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.seckill.goods.entity.GoodsOrder;
import com.wjs.seckill.goods.mapper.GoodsOrderMapper;
import com.wjs.seckill.goods.service.GoodsOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {


    @Transactional
    @Override
    public GoodsOrder queryById(Long id) {
        return null;
    }
}
