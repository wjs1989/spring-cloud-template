package com.wjs.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/get")
    public String hello() {
        return "success";
    }


    public static void main(String[] args) {
        int a = Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));

        System.out.println(a);
    }
}
