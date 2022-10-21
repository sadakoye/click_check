package com.clickAndCheck.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zzc
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /** Request Headers ： Authorization */
    private String header;

    /** Base64对该令牌进行编码 */
    private String base64Secret;

    /** 令牌过期时间 此处单位/毫秒 */
    private Long tokenValidityInSeconds;
}
