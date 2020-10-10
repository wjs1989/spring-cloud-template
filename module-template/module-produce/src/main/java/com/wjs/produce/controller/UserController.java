package com.wjs.produce.controller;

import com.wjs.produce.model.X;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

   // @Autowired
    //private CacheManager cacheManager;

    @Autowired
    private X x;

    @GetMapping("/info")
    public String info(){
        return x.getName();
    }
}
