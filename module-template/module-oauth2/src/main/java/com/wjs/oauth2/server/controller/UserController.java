package com.wjs.oauth2.server.controller;

import com.wjs.domain.service.user.vo.rsp.LoginVO;
import com.wjs.domain.service.user.vo.rsp.TokenVO;
import com.wjs.model.constant.MessageEnum;
import com.wjs.model.vo.BaseResult;
import com.wjs.oauth2.server.config.propertie.LoginProperties;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/18 17:17
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenStore tokenStore;

    @Value("${spring.application.name}")
    private String tokenServerName ;

    @Autowired
    private LoginProperties loginProperties;


    /**
     * 登录
     * @param request
     * @param vo
     * @return
     */
    @PostMapping("/login")
    public BaseResult<TokenVO> login(HttpServletRequest request, @RequestBody LoginVO vo){
        if(vo == null){
            return BaseResult.error("请输入请求参数");
        }

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("username", Collections.singletonList(vo.getUsername()));
        map.put("password", Collections.singletonList(vo.getPassword()));
        map.put("grant_type", Collections.singletonList(vo.getGrant_type()));
        map.put("scope", Collections.singletonList(vo.getScope()));
        map.put("client_id", Collections.singletonList(vo.getClient_id()));
        map.put("client_secret", Collections.singletonList(vo.getClient_secret()));
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, header);
        HttpEntity httpEntity = new HttpEntity(map, httpHeaders);

        ResponseEntity<OAuth2AccessToken> body = null;
        try{
            body = restTemplate.exchange(
                    String.format("http://%s/oauth/token",tokenServerName),
                    HttpMethod.POST,
                    httpEntity, OAuth2AccessToken.class);
        }catch (HttpClientErrorException e){
            log.error("登录异常：" ,e);
            return BaseResult.error();
        }

        if(body != null && body.getStatusCode() == HttpStatus.OK){
            return BaseResult.success(body.getBody());
        }else {
            return BaseResult.error("认证错误");
        }
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResult<?> logout(HttpServletRequest request)
    {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(authHeader))
        {
            return BaseResult.success();
        }

        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StringUtils.EMPTY).trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StringUtils.isEmpty(accessToken.getValue()))
        {
            return BaseResult.success();
        }

        // 清空 access token
        tokenStore.removeAccessToken(accessToken);

        // 清空 refresh token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);

        return BaseResult.success();
    }

    /**
     * 登录
     *
     * @param request
     * @param vo
     * @return
     */
    @PostMapping("/refreshToken")
    public BaseResult<OAuth2AccessToken> refreshToken(HttpServletRequest request, @RequestBody TokenVO vo) {
        if (vo == null || StringUtils.isBlank(vo.getRefreshToken())) {
            return BaseResult.error("请输入请求参数");
        }

        if (StringUtils.isBlank(vo.getClientId())) {
            vo.setClientId(loginProperties.getClientId());
        }

        if (StringUtils.isBlank(vo.getClientSecret())) {
            vo.setClientSecret(loginProperties.getClientSecret());
        }

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("grant_type", Collections.singletonList("refresh_token"));
        map.put("client_id", Collections.singletonList(vo.getClientId()));
        map.put("client_secret", Collections.singletonList(vo.getClientSecret()));
        map.put("refresh_token", Collections.singletonList(vo.getRefreshToken()));
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, header);
        HttpEntity httpEntity = new HttpEntity(map, httpHeaders);

        ResponseEntity<OAuth2AccessToken> body = null;
        try {
            body = restTemplate.exchange(
                    String.format("http://%s/oauth/token", tokenServerName),
                    HttpMethod.POST,
                    httpEntity, OAuth2AccessToken.class);
        } catch (HttpClientErrorException e) {
            log.error("登录异常：", e);
            return BaseResult.error();
        }
        if (body != null && body.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> additionalInformation = body.getBody().getAdditionalInformation();
            Object code = additionalInformation.get("code");
            if (code != null && !((Integer) code).equals(MessageEnum.SUCCESS.getCode())) {
                return BaseResult.error();
            }

            return BaseResult.success(body.getBody());
        } else {
            return BaseResult.error();
        }
    }


}
