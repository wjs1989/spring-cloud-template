package com.wjs.produce.service.impl;

import com.wjs.produce.proxy.DefProxy;
import com.wjs.produce.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @DefProxy
    @Override
    public void doQuery() {
        System.out.println("查询UserServiceImpl-》doQuery");
    }
}
