package com.wjs.oauth2.server.config;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

public class MyAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public Map<String, Object> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String,Object> re = (Map<String, Object>) super.convertAccessToken(token, authentication);
        // LoginUser principal = (LoginUser) authentication.getPrincipal();
        //
        // re.put(SecurityConstants.DETAILS_USER_ID,principal.getUserId());
        // re.put(SecurityConstants.DETAILS_USER_TYPE,principal.getUserType());
        // re.put(SecurityConstants.DETAILS_NAME,principal.getName());
        return re;
    }

}