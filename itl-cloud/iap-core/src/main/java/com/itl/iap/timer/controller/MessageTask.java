package com.itl.iap.timer.controller;

import com.itl.iap.timer.util.HttpUtil;
import com.itl.iap.timer.common.TimerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:杜海涛 
 * @Created on 2017年7月31日
 * @description :
 *
 */

//@Component
//@EnableScheduling
public class MessageTask {
	private static final Logger logger = LoggerFactory.getLogger(MessageTask.class);


	@Scheduled(cron = "0 0/1 * * * ?")
	protected void messageTask() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				logger.debug(new Date()+
						"begin this is delay scheduler task runing  "
						);

				String messageUrl = TimerConstants.foreign;
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("method", TimerConstants.messageMethod);
				String str = HttpUtil.javahttpPost(map, messageUrl);
				
				logger.debug("返回值="+str);	
				
				logger.debug(new Date()+
						"end this is delay scheduler task runing  "
						);
			}
		}).start();
		
	}
	
	
}






