package com.wjs.seata.lottery;

import com.wjs.seata.P88Helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class HistoryLottery {

    public void init(){

    }

    public static void main(String[] args) throws Exception{
        Set<String> sb = new HashSet<>();
        FileReader fr = new FileReader("D:\\aa.txt");
        BufferedReader bf = new BufferedReader(fr);
        String str;
        // 按行读取字符串
        while ((str = bf.readLine()) != null) {
            sb.add(str.split(",")[0]);
        }
        bf.close();
        fr.close();

        System.out.println(sb);
    }

}
