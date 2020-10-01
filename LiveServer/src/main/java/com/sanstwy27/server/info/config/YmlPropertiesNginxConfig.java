package com.sanstwy27.server.info.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sanstwy27
 * @create 10/1/2020
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "nginx")
public class YmlPropertiesNginxConfig {

    private String statPath;

    public String getStatPath() {
        return statPath;
    }

    public void setStatPath(String statPath) {
        this.statPath = statPath;
    }
}
