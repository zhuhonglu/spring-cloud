package com.itl.iap.test.controller;
import com.itl.iap.test.service.RibbonHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonHystrixController {
  @Autowired
  private RibbonHystrixService ribbonHystrixService;
  @Autowired
  private RestTemplate restTemplate;



  @RequestMapping(value = "/test")
  public String info1() {
    System.out.print("----------开始调用其他系统test");
    return restTemplate.getForEntity("http://iap-core/test", String.class).getBody();
  }
  @GetMapping("/ribbon/{id}")
  public String findById(@PathVariable String id) {
    System.out.print("----------开始调用其他系统api0");
    return this.ribbonHystrixService.findById(id);
  }
  @GetMapping("/hystrix/{id}")
  public String HystrixCommand(@PathVariable String id) {
    System.out.print("----------开始调用其他系统api0");
    return this.ribbonHystrixService.HystrixCommand(id);
  }
  @RequestMapping("/demo")
  public String info(){
    return "i'm demo-timer";
  }
}
