package com.setkies.sinp.global.config;

import com.setkies.sinp.global.config.properties.AuthProperties;
import com.setkies.sinp.infrastructure.jwt.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        JwtProperties.class,
        AuthProperties.class
})
public class PropertiesConfig {
}