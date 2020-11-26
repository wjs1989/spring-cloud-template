package com.wjs.wechat.controller;

import cn.hutool.json.JSONObject;
import com.wjs.model.vo.BaseResult;
import com.wjs.wechat.constant.WechatMiniUrl;
import com.wjs.wechat.util.AesCbcUtil;
import com.wjs.wechat.vo.req.MiniUserDecoder;
import com.wjs.wechat.vo.resp.MiniUserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序
 *
 * @author wenjs
 */

@RestController
@RequestMapping("/login")
public class WechatMiniLoginController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/decodeUserInfo")
    public BaseResult<MiniUserResp> decodeUserInfo(@RequestBody MiniUserDecoder user) {
        if (user.getCode() == null || user.getCode().length() == 0) { 
            return BaseResult.error("code 不能为空");
        }

        String appid = "wxeb20873968183f4c";
        String secret = "105a9574e4ff17f6978a2f25d853442e";
        // 授权（必填）
        String grant_type = "authorization_code";

        // 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 发送请求
        String url = WechatMiniUrl.JSCODE2SESSION + String.format("?appid=%s&secret=%s&js_code=%s&grant_type=%s",
                appid, secret, user.getCode(), grant_type);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (entity.getStatusCode() == HttpStatus.OK) {
            String sr = entity.getBody();
            // 解析相应内容（转换成json对象）
            JSONObject jsonSession = new JSONObject(sr);
            // 获取会话密钥（session_key）
            String session_key = jsonSession.get("session_key").toString();
            // 用户的唯一标识（openid）
            String openid = jsonSession.get("openid").toString();

            // 2、对encryptedData加密数据进行AES解密
            try {
                String result = AesCbcUtil.decrypt(user.getEncryptedData(), session_key, user.getIv(), "UTF-8");
                if (null != result && result.length() > 0) {
                    JSONObject resultJSON = new JSONObject(result);
                    MiniUserResp miniUserResp = resultJSON.toBean(MiniUserResp.class);
                    return BaseResult.success(miniUserResp);
                } else {
                    return BaseResult.error("解密失败");
                }
            } catch (Exception e) {
            }
        }
        return BaseResult.error(entity.getBody());
    }

}
