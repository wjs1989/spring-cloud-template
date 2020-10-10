package com.wjs.seckill;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.xmlunit.util.Convert;

import java.io.ObjectOutputStream;

//@SpringBootTest
class ModuleSeckillApplicationTests {

    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        int COUNT_BITS = Integer.SIZE - 3;
        System.out.println(COUNT_BITS);
        System.out.println(Integer.toBinaryString(COUNT_BITS) );
        System.out.println(Integer.toBinaryString((1 << COUNT_BITS)) );

        int CAPACITY   = (1 << COUNT_BITS) - 1;
        System.out.println(CAPACITY);


        System.out.println(Integer.toBinaryString(CAPACITY) );

        System.out.println(Integer.toBinaryString( -1<<1) );
    }

}
