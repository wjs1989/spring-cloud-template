package com.wjs.produce.controller;

import com.wjs.produce.model.X;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

@RestController
public class UserController {

   // @Autowired
    //private CacheManager cacheManager;

    @Autowired
    private X x;

    @GetMapping("/info")
    public String info(){

        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return localDateTime.format(dtf);
    }
}
