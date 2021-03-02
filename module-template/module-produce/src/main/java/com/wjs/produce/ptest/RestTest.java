package com.wjs.produce.ptest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author wenjs
 */
public class RestTest {

    public static void main(String[] args) {
        TokenServer tokenServer = new TokenServer();

// while (true){
//     tokenServer.getToken();
// }

        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for (int i = 0; i <15 ; i++) {
            executorService.submit(() -> {
                while (true) {
                    tokenServer.getToken();
                }
            });
        }
    }


}
