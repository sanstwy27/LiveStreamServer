package com.sanstwy27.server.info.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sanstwy27
 * @create 9/30/2020
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "twitch")
public class YmlPropertiesTwitchConfig {

    private String clientId;
    private String clientSecret;
    private String grantType;
    private YmlTwitchUrl apiUrl;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public YmlTwitchUrl getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(YmlTwitchUrl apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String toString() {
        return "YmlPropertiesTwitchConfig{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", grantType='" + grantType + '\'' +
                ", apiUrl=" + apiUrl +
                '}';
    }
}
