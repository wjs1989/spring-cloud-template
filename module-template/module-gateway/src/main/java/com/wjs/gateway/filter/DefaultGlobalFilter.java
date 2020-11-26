package com.wjs.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.wjs.model.vo.BaseResult;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 */
//@Component
public class DefaultGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if (cookies.containsKey("aaa")) {
            return chain.filter(exchange);
        }


        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        String msg = "unknow cookies aaa";
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(BaseResult.error(msg)));
        }));

       // return response.setComplete();
    }
}
