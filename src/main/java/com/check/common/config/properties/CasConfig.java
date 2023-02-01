package com.check.common.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * cas配置类
 *
 * @author zzc
 */
@Component
@ConfigurationProperties(prefix = "config.cas")
@Data
public class CasConfig {

    String service;
    String casUrl;
    String url;
    String loginSuccess;
    String loginError;
    String loginIp;
    String appId;
    String appKey;
    String urlHead;
    String homePage;
}
