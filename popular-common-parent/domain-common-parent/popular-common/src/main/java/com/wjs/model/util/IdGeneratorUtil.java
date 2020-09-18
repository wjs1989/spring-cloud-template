package com.wjs.model.util;

/**
 * Created by Administrator on 2017/4/25.
 */

import com.wjs.model.keygen.KeyGenerator;
import com.wjs.model.text.IPKeyGenerator;


public class IdGeneratorUtil {

    private final static KeyGenerator defaultGenerator = new IPKeyGenerator();

    public static synchronized long nextId() {
       return defaultGenerator.generateKey().longValue();
    }
}
