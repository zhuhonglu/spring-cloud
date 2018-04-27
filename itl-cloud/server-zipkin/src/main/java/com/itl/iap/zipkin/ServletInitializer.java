package com.itl.iap.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServerZipkinApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ServerZipkinApplication.class, args);
	}


}
