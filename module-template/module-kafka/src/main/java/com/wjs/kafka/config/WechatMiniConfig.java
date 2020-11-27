package com.wjs.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
//@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(WechatMiniConfig.PREFIX)
public class WechatMiniConfig {

    public static final String PREFIX = "wechat";

    private List<MiniConfig> minis = new ArrayList();

    public List<MiniConfig> getMinis() {
        return minis;
    }

    public void setMinis(List<MiniConfig> minis) {
        this.minis = minis;
    }
}
