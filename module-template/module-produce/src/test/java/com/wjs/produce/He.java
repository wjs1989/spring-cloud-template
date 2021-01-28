package com.wjs.produce;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class He {
    public static void main(String[] args) {
      Set<String> SET_METHODS = (Set) Arrays.stream(PreparedStatement.class.getDeclaredMethods()).filter((method) -> {
            return method.getName().startsWith("set");
        }).filter((method) -> {
            return method.getParameterCount() > 1;
        }).map(Method::getName).collect(Collectors.toSet());


        SET_METHODS.forEach(p-> System.out.println(p));
    }
}
