package com.wjs.sentinel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {
    private AtomicInteger index = new AtomicInteger();

    @GetMapping("/get")
    public String hello()
    {
        log.info("get->log:" + index.getAndIncrement());
        return "success";
    }


    public static void main(String[] args) {
        int a = Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));

        System.out.println(a);
    }
}
