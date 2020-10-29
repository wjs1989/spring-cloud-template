package com.wjs.produce.process;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ParentMain {
    public static void main(String[] args) throws IOException, InterruptedException {
      //  Runtime runtime = Runtime.getRuntime();
      //  runtime.exec("java childMain");

        DataStore.put("1","123");
        DataStore.put("2","1231");

        JavaProcess.exec(ChildMain.class, Arrays.asList("-Xmx200m"), Arrays.asList("argument"));
    }
}
