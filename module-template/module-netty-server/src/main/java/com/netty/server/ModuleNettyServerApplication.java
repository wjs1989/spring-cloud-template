package com.netty.server;

import com.netty.server.model.XModel; 

public class ModuleNettyServerApplication {

    public static void main(String[] args) throws InterruptedException {

        XModel xModel = new XModel();

        xModel.setName("wenjs");

        System.out.println("hello:"+xModel.getName());

        Thread.sleep(1000);

        System.out.println("hello:"+xModel.getName());

    }

}
