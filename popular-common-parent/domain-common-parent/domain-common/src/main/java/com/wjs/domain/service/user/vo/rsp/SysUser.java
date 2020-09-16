package com.wjs.domain.service.user.vo.rsp;

/**
 * 用户对象 sys_user
 * 
 * @author wenjs
 */
public class SysUser
{

    /** 用户ID */
    private Long userId;

    private String userName;

    /** 密码 */
    private String password;

    public SysUser()
    {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
