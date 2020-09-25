package com.wjs.kafka.dto;

public class OrderDto {

    private Long orderId;
    private Long userId;
    private Long goodsId;
    private Integer num;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public static Builder createBuilder(){
        return new Builder();
    }

    public static class Builder {
        private Long orderId;
        private Long userId;
        private Long goodsId;
        private Integer num = 1;

        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }
        public Builder setGoodsId(Long goodsId) {
            this.goodsId = goodsId;
            return this;
        }
        public Builder setNum(Integer num) {
            this.num = num;
            return this;
        }

        public OrderDto build() {
            OrderDto orderDto = new OrderDto();
            orderDto.goodsId = goodsId;
            orderDto.userId = userId;
            orderDto.num = num;
            orderDto.orderId = orderId;

            return orderDto;
        }
    }
}
