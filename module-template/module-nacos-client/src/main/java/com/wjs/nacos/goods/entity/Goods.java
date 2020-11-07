package com.wjs.nacos.goods.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Goods implements Serializable{

    private Long id;

    private Long categoryId;

    private String title;

    private String description;

    private Integer total;

    private Integer number;

    private BigDecimal price;

    private String cover;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //乐观锁
    @Version
    private Long version;

    private Integer valid;

}
