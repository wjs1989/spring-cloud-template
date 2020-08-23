package com.wjs.oauth2.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/17 10:28
 */
@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityContorller {

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        log.info("security server check================>>>" + principal.toString());
        return principal;
    }
}
