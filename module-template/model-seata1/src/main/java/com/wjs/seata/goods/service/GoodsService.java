package com.wjs.seata.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjs.seata.goods.entity.Goods;

import java.util.List;

/**
 * @author wenjs
 */
public interface GoodsService extends IService<Goods> {

    Long seckill(Long goodsId, Integer num);
}
