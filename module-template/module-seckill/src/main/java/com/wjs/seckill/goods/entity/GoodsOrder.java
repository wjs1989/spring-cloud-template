package com.wjs.seckill.goods.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsOrder {

    private Long id;
    private Long userId;
    private BigDecimal amount;
    private Integer status;
    private Long addressId;
    private Long expressId;
    private String expressNo;
    private String tradeNo;
    private String tradeExt;
    private Date createTime;
    private Date updateTime;

}
