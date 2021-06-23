package com.wjs.produce.config;

import com.wjs.produce.model.X;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class CookieConfig implements InitializingBean {
    @Autowired
    public X x1;
    @Autowired
    public X x2;

    public CookieConfig(){
        System.out.println("CookieConfig");
    }

    public void addCookie(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        Cookie cookie = new Cookie("sessionId",sessionId);
        cookie.setHttpOnly(true);
        cookie.setPath(request.getContextPath()+"/");
        cookie.setDomain("wjs.com");
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(x1.getName());
        System.out.println(x2.getName());
    }
}
