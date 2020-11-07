package com.wjs.seata4.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjs.seata4.goods.entity.Goods;

/**
 * @author wenjs
 */
public interface GoodsService  {

    Long seckill(Long goodsId, Integer num);
    void dynamic();
}
