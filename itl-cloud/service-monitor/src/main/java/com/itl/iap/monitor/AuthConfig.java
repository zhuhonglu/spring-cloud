package com.itl.iap.monitor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 */
@ConfigurationProperties(prefix = "security.user", ignoreInvalidFields=true)
@Component
public class AuthConfig {
	/**
	 * 用户名
	 */
	public String name= "admin";
	/**
	 * 密码
	 */
    public String password="admin";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
