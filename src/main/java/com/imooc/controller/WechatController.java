package com.imooc.controller;

import com.google.common.net.UrlEscapers;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by: xmg
 * 2018/3/29 15:53
 */
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl)  {
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        String redirectUrl = null;
		try {
			redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl,"uft-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:" + redirectUrl;
    }


    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openid;

    }

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam() String returnUrl) {
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qruserInfo";
        String redirectUrl = wxOpenService.oauth2buildAuthorizationUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;

    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openid;

    }
}
