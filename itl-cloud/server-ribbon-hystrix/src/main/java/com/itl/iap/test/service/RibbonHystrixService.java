package com.itl.iap.test.service;
import com.itl.iap.test.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/*import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;*/

@Service
public class RibbonHystrixService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  private static final Logger LOGGER = LoggerFactory.getLogger(RibbonHystrixService.class);



  /**
   * 使用ribbon去负载均衡调用服务端服务
   * @param id id
   * @return 通过id查询到的用户
   */
  public String findById(String id) {
    System.out.print("----------开始调用其他系统api");
    return restTemplate.getForObject(
            "http://iap-core/" + id, String.class);
  }

  /**
   * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
   * @param id id
   * @return 通过id查询到的用户
   * BaseThreadPool表示线程池
   */
  @HystrixCommand(groupKey="BaseThreadPool",fallbackMethod = "fallback")
  public String HystrixCommand(String id) {
    return restTemplate.getForObject("http://iap-core/" + id, String.class);
  }
  /**
   * hystrix fallback方法
   * @param id id
   * @return 默认的用户
   */
  public String fallback(String id) {
    RibbonHystrixService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);

    return "异常发生，进入fallback方法，接收的参数：id = {}"+id;
  }
}
