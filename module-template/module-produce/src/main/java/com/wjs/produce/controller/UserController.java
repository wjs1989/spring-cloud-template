package com.wjs.produce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/info")
    public String info(){
        return "hello word";
    }
}
