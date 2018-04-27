package com.itl.iap.timer.controller;

import com.itl.iap.timer.dao.Position;
import com.itl.iap.timer.service.IPositionService;
import com.itl.iap.timer.util.MyLogger;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.cloud.client.ServiceInstance;*/
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by luzhuhong on 2018/4/19.
 */
@RestController
public class PositionController {
    private static final MyLogger LOGGER = new MyLogger(PositionController.class);
   /* @Autowired
    private DiscoveryClient discoveryClient;//服务发现客户端*/
    @Autowired(required=true)
    IPositionService positionService;
    @RequestMapping(value = "/api/position/query")
    @ResponseBody
    public List<Position> getPosition(HttpServletRequest request, Position position) {

     return positionService.getPosition();
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable String id) {
       /* ServiceInstance instance=discoveryClient*/
        return "您好 您输入的数据为"+id;
    }
    @RequestMapping("/test")
    public String findById1() {
        return "您好 您输入的数据为";
    }
}