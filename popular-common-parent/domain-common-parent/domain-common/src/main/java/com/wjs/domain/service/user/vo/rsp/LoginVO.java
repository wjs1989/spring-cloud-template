package com.wjs.domain.service.user.vo.rsp;

import lombok.Data;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/20 10:42
 */
@Data
public class LoginVO {
    private String username;
    private String password;
    private String grant_type;
    private String scope;
    private String code;
    private String client_id;
    private String client_secret;

}
