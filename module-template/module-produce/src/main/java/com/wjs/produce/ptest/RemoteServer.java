package com.wjs.produce.ptest;

import org.springframework.web.client.RestTemplate;

/**
 * @author wenjs
 */
public class RemoteServer {

    private RestTemplate restTemplate = new RestTemplate();

    public static RemoteServer getInstance(){
        return RemoteServerInstance.Instance;
    }

    public static class RemoteServerInstance{
        public static RemoteServer Instance = new RemoteServer();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
