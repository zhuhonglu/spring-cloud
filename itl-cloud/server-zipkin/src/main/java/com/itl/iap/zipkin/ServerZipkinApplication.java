package com.itl.iap.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@RestController
//rabbitMQ
//@EnableZipkinStreamServer
public class ServerZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerZipkinApplication.class, args);
	}
	@GetMapping("/api/test4")
	public String test4(){
		return "test4";
	}
}
