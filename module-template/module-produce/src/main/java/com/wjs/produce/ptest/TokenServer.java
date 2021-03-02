package com.wjs.produce.ptest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

/**
 * @author wenjs
 */
public class TokenServer {

    private String account = "dev";
    private String password = "123456";

    public String getToken() {
        String url = EndPointConstant.LOGIN_URL;
        String params = "{\"username\":\"dev\",\"password\":\"123456\",\"sysType\":\"1\",\"userType\":[\"1\",\"2\",\"3\",\"4\",\"5\"]}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<BaseResult<AccessToken>> exchange = RemoteServer.getInstance().getRestTemplate().exchange(url, HttpMethod.POST,
                new HttpEntity<>(params, headers),
                new ParameterizedTypeReference<BaseResult<AccessToken>>() {
                });
        String token="";
        if(Objects.nonNull(exchange.getBody())){
         //   token=exchange.getBody().getData().getAccess_token();
        }
        return token;
    }
}
