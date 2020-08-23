package com.wjs.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/19 15:44
 */
public class AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        Map<String, Object> map = new HashMap<String, Object>();
        Throwable cause = e.getCause();
        if(cause instanceof InvalidTokenException) {
            map.put("code", 10001);//401
            map.put("msg", "无效的token");
        }else{
            map.put("code", 10002);//401
            map.put("msg", "访问此资源需要完全的身份验证");
        }
        map.put("data", e.getMessage());
        map.put("success", false);
        map.put("path", httpServletRequest.getServletPath());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(httpServletResponse.getOutputStream(), map);
        } catch (Exception ex) {
            throw new ServletException();
        }
    }
}
