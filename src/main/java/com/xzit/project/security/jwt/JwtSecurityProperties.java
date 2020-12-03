package com.xzit.project.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtSecurityProperties {

    /** Base64对该令牌进行编码 */
    private String base64Secret;

    /** 令牌过期时间 此处单位/毫秒 */
    private Long tokenValidityInSeconds;

    private Long tokenValidityInSecondsForRememberMe;

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public Long getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(Long tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public Long getTokenValidityInSecondsForRememberMe() {
        return tokenValidityInSecondsForRememberMe;
    }

    public void setTokenValidityInSecondsForRememberMe(Long tokenValidityInSecondsForRememberMe) {
        this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
    }
}
