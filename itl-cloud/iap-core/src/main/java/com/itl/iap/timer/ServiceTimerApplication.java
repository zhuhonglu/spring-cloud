package com.itl.iap.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@RestController
public class ServiceTimerApplication {
	private final Logger log = LoggerFactory.getLogger(ServiceTimerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceTimerApplication.class, args);
	}

	@RequestMapping("/infoes")
	public String info(){
		return "i'm store-timer";
	}

	@Bean
	@LoadBalanced RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	public AlwaysSampler defaultSampler(){
//		return new AlwaysSampler();
//	}

}
