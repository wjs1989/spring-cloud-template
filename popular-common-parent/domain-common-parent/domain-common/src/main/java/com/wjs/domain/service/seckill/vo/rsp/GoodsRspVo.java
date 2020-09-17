package com.wjs.domain.service.seckill.vo.rsp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsRspVo implements Serializable {
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
}
