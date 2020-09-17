package com.wjs.seckill.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjs.seckill.goods.entity.Goods;

import java.util.List;

/**
 * @author wenjs
 */
public interface GoodsService extends IService<Goods> {

    Goods querySeckillGoodsById(Long id) throws Exception;
    List<Goods> querySeckillGoods();
}
