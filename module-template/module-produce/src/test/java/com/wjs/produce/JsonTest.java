package com.wjs.produce;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class JsonTest {


    public static void main(String[] args) throws FileNotFoundException {
        int b;
        try {
            System.out.println("please Input:");
            while ((b = System.in.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}
