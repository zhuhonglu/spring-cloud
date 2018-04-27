package com.itl.iap.monitor;

import net.bull.javamelody.CollectorServlet;
import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenshengpeng on 2017/8/8.
 */

@Component
@AutoConfigureAfter(AuthConfig.class)
public class JavaMelodyMonitor {

    @Autowired
    private AuthConfig authConfig;

    @Bean public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new MonitoringFilter());
        registration.addUrlPatterns("/*");
        //        <async-supported>true</async-supported> 和 <dispatcher>ASYNC</dispatcher> 用于在servlet3.0中处理异步请求
        registration.setAsyncSupported(true);
        Map<String, String > initParameters = new HashMap<String, String>();
        initParameters.put("url-exclude-pattern", "/webjars/.*");
        initParameters.put("authorized-users", authConfig.getName() + ":" + authConfig.getPassword());
        registration.setInitParameters(initParameters);
        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
        return registration;
    }

    @Bean public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new SessionListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean getCollectorServlet(){
        CollectorServlet collectorServlet = new CollectorServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(collectorServlet);
        Map<String, String > initParameters = new HashMap<String, String>();
//        initParameters.put("authorized-users", authConfig.getName() + ":" + authConfig.getPassword());
        registrationBean.setInitParameters(initParameters);
        List<String> urlMappings=new ArrayList<String>();
        urlMappings.add("/monitoringCollect");////访问，可以添加多个
        registrationBean.setUrlMappings(urlMappings);
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
}
