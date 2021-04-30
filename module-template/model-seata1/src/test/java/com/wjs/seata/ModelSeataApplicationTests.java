package com.wjs.seata;

import io.seata.rm.tcc.remoting.parser.DubboUtil;
import io.seata.spring.util.SpringProxyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class ModelSeataApplicationTests {

    public static void main(String[] args) {
        double a = 25/2;
        System.out.println(a);
    }

}
