package com.itl.iap.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * 使用@EnableCircuitBreaker注解开启断路器功能
 * @author luzhuhong
 */
@EnableDiscoveryClient        //通过该注解，实现服务发现，注册
@SpringBootApplication
@EnableCircuitBreaker            //增加的断路器注解
/*@EnableFeignClients //启动Feign负载均衡
@EnableHystrix  //增加的断路器注解
@RestController*/
public class RibbonHystrixApplication {
  /**
   * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
   * @return restTemplate
   */
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(RibbonHystrixApplication.class, args
    );
  }
}
