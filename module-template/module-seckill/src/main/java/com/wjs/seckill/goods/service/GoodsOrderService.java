package com.wjs.seckill.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjs.seckill.goods.entity.GoodsOrder;

public interface GoodsOrderService extends IService<GoodsOrder> {

    GoodsOrder queryById(Long id);
}
