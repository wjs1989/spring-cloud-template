package com.wjs.produce.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CookieConfig {

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
}
