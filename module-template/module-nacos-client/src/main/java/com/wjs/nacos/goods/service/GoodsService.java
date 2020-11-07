package com.wjs.nacos.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjs.nacos.goods.entity.Goods;

/**
 * @author wenjs
 */
public interface GoodsService extends IService<Goods> {

    void scekillRemote1(Long goodsId, Integer num);
}
