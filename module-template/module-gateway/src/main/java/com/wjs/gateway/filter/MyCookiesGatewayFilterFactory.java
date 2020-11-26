package com.wjs.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 局部过滤器
 * 命名规则 xxxGatewayFilterFactory
 */
@Component
public class MyCookiesGatewayFilterFactory extends AbstractGatewayFilterFactory<MyCookiesGatewayFilterFactory.Config> {
    public static final String ENABLE_KEY = "enable,name";

    public MyCookiesGatewayFilterFactory() {
        super(MyCookiesGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(ENABLE_KEY);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if(!config.enable){
                return chain.filter(exchange);
            }

            System.out.println("-------------MyCookiesFilter----------------");
            System.out.println(config.enable);
            System.out.println(config.name);
            return chain.filter(exchange);
        };
    }

    public static class Config {
        private boolean enable;
        private String name;

        public Config() {
        }
        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
