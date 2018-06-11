package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by: xmg
 * 2018/4/2 10:21
 */
@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {
    // 微信公众平台授权url
    private String wechatMpAuthorize;

    //微信开放平台授权url
    private String wechatOpenAuthorize;

    // 点餐系统
    private  String sell;
}
