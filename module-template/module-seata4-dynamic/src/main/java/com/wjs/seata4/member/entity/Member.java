package com.wjs.seata4.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class Member {

    private Long id;
    private String name;
    private Long goodsId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
