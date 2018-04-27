package com.itl.iap.timer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//使用环境中的配置覆盖基本配置，如果无环境配置，则获取默认配置。
@PropertySource(value = {"classpath:/config/jdbc.properties",
		"classpath:/config/jdbc-${spring.profiles.active}.properties",
		"classpath:/config/redis.properties",
		"classpath:/config/redis-${spring.profiles.active}.properties"})
public class WebConfig {


}