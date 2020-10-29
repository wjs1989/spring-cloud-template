package com.wjs.produce.process;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChildMain {
    public static void main(String[] args) throws IOException {

        FileOutputStream fOut = new FileOutputStream("c:\\wjs.txt");
        ConcurrentHashMap<String, String> cache = DataStore.getCache();

        Iterator<Map.Entry<String, String>> iterator = cache.entrySet().iterator(); 
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            fOut.write(next.getKey().getBytes());
            fOut.write(next.getValue().getBytes());
        }
        fOut.close();
    }
}
