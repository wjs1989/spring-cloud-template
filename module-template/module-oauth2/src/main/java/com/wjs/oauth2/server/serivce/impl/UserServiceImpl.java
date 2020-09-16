package com.wjs.oauth2.server.serivce.impl;

import com.wjs.domain.service.user.vo.rsp.SysUser;
import com.wjs.domain.service.user.vo.rsp.UserInfo;
import com.wjs.model.vo.BaseResult;
import com.wjs.oauth2.server.domain.LoginUser;
import com.wjs.remote.feign.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/17 10:35
 */

@Service
public class UserServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        BaseResult<UserInfo> userInfo = remoteUserService.getUserInfo(userName);
        checkUser(userInfo, userName);
        return getUserDetails(userInfo);
    }

    public void checkUser(BaseResult<UserInfo> userResult, String username)
    {
        if (userResult == null || userResult.getData() == null)
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
    }

    private UserDetails getUserDetails(BaseResult<UserInfo> result)
    {
        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<String>();
        if (info.getRoles() != null) {
            dbAuthsSet.addAll(info.getRoles());
        }
        if (info.getPermissions() != null) {
            dbAuthsSet.addAll(info.getPermissions());
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        return new LoginUser(user.getUserId(), user.getUserName(), user.getPassword(), true, true, true, true,
                authorities);
    }

}
