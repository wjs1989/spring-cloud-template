package com.wjs.kafka.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjs.kafka.dto.OrderDto;
import com.wjs.model.vo.BaseResult;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogAccessor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/kafka")
public class SendController {
    protected final LogAccessor logger =  new LogAccessor(LogFactory.getLog(this.getClass()));
    @Autowired
    private KafkaTemplate kafkaTemplate;


    @GetMapping("/send")
    public BaseResult<String> getSeckillInfo() throws Exception{
        OrderDto orderDto = OrderDto.createBuilder()
                .setGoodsId(111L)
                .setUserId(222L)
                .setOrderId(333L)
                .build();




        ListenableFuture send = kafkaTemplate.send("topictest", JSONObject.toJSONString(orderDto));

        return BaseResult.success();
    }

}
