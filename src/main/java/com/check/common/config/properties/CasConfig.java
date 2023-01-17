package com.check.common.config.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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
    String loginSuccess;
    String loginError;
    String loginIp;
}
