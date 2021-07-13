package com.wjs.domain.service.user.vo.rsp;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/20 10:45
 */
public class TokenVO {

    //@ApiModelProperty(value = "refresh_token")
    private String refreshToken;

    //@ApiModelProperty(value = "客户端id")
    private String clientId;

    //@ApiModelProperty(value = "客户端secret")
    private String clientSecret;


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
