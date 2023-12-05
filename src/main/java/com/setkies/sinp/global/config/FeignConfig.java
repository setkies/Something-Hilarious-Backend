package com.setkies.sinp.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.setkies.sinp.infrastructure")
@Configuration
public class FeignConfig {

}
