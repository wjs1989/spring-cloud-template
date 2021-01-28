package com.wjs.produce;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Properties;


public class ToolsTest {

    @Test
    public void Test1() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties("application.yml", ClassUtils.getDefaultClassLoader());
        System.out.println(properties);
    }

}
