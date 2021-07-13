package com.wjs.oauth2.server.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * accesstoken 附加信息
 * wenjs
 */
public class DefaultTokenEnhancer implements TokenEnhancer { 

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        // LoginUser loginUser = (LoginUser)oAuth2Authentication.getPrincipal();
        // if(loginUser != null){
        //
        //     Map<String, Object>  additionalInformation =  oAuth2AccessToken.getAdditionalInformation();
        //     if(additionalInformation== null ||additionalInformation.isEmpty()){
        //         additionalInformation = new LinkedHashMap();
        //         additionalInformation.put(SecurityConstants.DETAILS_USER_ID,loginUser.getUserId());
        //         additionalInformation.put(SecurityConstants.DETAILS_USER_ROLES,loginUser.getRoles());
        //         additionalInformation.put(SecurityConstants.DETAILS_USER_TYPE,loginUser.getUserType());
        //         additionalInformation.put(SecurityConstants.DETAILS_NAME,loginUser.getName());
        //
        //         if(oAuth2AccessToken instanceof DefaultOAuth2AccessToken){
        //              ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInformation);
        //         }else {
        //         }
        //     }
        // }
        return oAuth2AccessToken;
    }
}
