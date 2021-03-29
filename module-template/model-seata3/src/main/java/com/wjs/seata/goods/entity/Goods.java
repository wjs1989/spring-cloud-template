package com.wjs.seata.goods.entity;
  

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter
public class Goods implements Serializable{

    private Long id;

    private Long categoryId;

    private String title;

    private String description;

    private Integer total;

    private Integer number;

    private BigDecimal price;

    private String cover;

    private Date createTime;

    private Date updateTime;

    //乐观锁
    private Long version;

    private Integer valid;

}
