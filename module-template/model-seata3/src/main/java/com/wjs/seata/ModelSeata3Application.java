package com.wjs.seata;

import com.wjs.seata.goods.entity.Goods;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ModelSeata3Application {

    public static void main(String[] args) {
        // SpringApplication.run(ModelSeata3Application.class, args);

        Goods goods = new Goods();
        goods.setNumber(19);

        System.out.println(goods.getNumber());


    }

}
