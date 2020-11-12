package com.wjs.produce.controller;

import com.wjs.produce.model.X;
import com.wjs.produce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

@RestController
public class UserController {

   // @Autowired
    //private CacheManager cacheManager;

    @Autowired
    private X x;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String info(){
        userService.doQuery();

        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        restTemplate.getForObject("http://118.24.22.139:8080/a/b",String.class);
        return localDateTime.format(dtf);
    }
}
