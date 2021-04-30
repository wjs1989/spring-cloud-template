package com.wjs.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.wjs.mqtt.network.NetworkType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class ModuleMqttApplicationTests {

    @Test
    void contextLoads() {

        NetworkType abv = NetworkType.of("ABV");
        System.out.println(JSONObject.toJSONString(abv));

    }

}
