package com.wjs.produce.controller;

import com.wjs.produce.model.X;
import com.wjs.produce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

@RestController
public class UserController {

   // @Autowired
    //private CacheManager cacheManager;
   @Autowired
   private RedisTemplate<Object, Object> redisTemplate;

    //@Autowired
    private X x;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Object info(String key){
        // userService.doQuery();
        //
        // LocalDateTime localDateTime = LocalDateTime.now();
        //
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //
        // restTemplate.getForObject("http://118.24.22.139:8080/a/b",String.class);
      //  return localDateTime.format(dtf);

       return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<X>> getUsers(@PathVariable("id") Long id) {

        return Mono.just(new X("wjs"))
                .map(acc -> new ResponseEntity<>(acc, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(null, HttpStatus.NOT_FOUND)));
    }
}
