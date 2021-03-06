package com.wjs.seata4.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjs.seata4.goods.entity.GoodsOrder;
import com.wjs.seata4.goods.mapper.GoodsOrderMapper;
import com.wjs.seata4.goods.service.GoodsOrderService;
import org.springframework.stereotype.Service;

@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {

}
