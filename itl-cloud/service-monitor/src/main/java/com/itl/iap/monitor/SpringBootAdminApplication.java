/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itl.iap.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
public class SpringBootAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminApplication.class, args);
	}



	@Configuration
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// Page with login form is served as /login.html and does a POST on /login
			http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
			// The UI does a POST on /logout on logout
			http.logout().logoutUrl("/logout");
			// The ui currently doesn't support csrf
			http.csrf().disable();

			// Requests for the login page and the static assets are allowed
			http.authorizeRequests()
				.antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
				.permitAll();
			// ... and any other request needs to be authorized
			http.authorizeRequests().antMatchers("/**").authenticated();

			// Enable so that the clients can authenticate via HTTP basic for registering
			http.httpBasic();
		}
	}
	// end::configuration-spring-security[]
}
// end::application-eureka[]
