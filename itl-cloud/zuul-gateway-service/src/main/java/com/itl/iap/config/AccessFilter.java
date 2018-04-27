package com.itl.iap.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * filterOrder:filter执行顺序，通过数字指定
   shouldFilter:filter是否需要执行 true执行 false 不执行
  run : filter具体逻辑
  filterType :filter类型,分为以下几种

 pre:请求执行之前filter
 route: 处理请求，进行路由
 post: 请求处理完成后执行的filter
 error:出现错误时执行的filter
 by luzhuhong
 */
@Component
public class AccessFilter extends ZuulFilter  {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        return false;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("----------"+String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("access_token");
        if(accessToken == null) {
            log.warn("access token is empty12");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}