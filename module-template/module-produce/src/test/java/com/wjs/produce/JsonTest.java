package com.wjs.produce;

import org.springframework.util.ReflectionUtils;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;
import sun.reflect.misc.ReflectUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
