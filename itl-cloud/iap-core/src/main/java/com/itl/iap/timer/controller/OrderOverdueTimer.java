package com.itl.iap.timer.controller;

import java.util.List;

import com.itl.iap.timer.util.MyLogger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/** 
* @author 作者 Fei.chu 
* @version 创建时间：2017年7月27日 下午2:09:06 
* 说明  订单过期定时任务
*/
@Component
public class OrderOverdueTimer {
	 /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(OrderOverdueTimer.class);
    //第一次延迟1秒执行，当执行完后30秒再执行
    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public void timerInit() {

    }

}
