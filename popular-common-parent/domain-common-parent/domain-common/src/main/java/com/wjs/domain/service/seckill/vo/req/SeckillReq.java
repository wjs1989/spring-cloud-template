package com.wjs.domain.service.seckill.vo.req;

import lombok.Data;

@Data
public class SeckillReq {
    private Long userId;
    private Long goodsId;
    private Integer num;
}
