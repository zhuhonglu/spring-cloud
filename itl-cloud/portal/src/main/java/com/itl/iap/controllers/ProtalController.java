package com.itl.iap.controllers;

import com.itl.iap.pojo.SystemUser;
import com.itl.iap.service.SystemUserService;
import com.itl.iap.util.MyLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by luzhuhong on 2018/4/24.
 */
@Api(value="门户网站api类", description = "门户网站api操作")
@RestController
public class ProtalController {

    private static final MyLogger LOGGER = new MyLogger(ProtalController.class);
    @Autowired
    SystemUserService service;

   /* @RequestMapping("/protal")
    public String index(HttpServletRequest request, Model model) {
        return "protal";
    }
*/
   @ApiOperation(value="获取首页", notes="")
   @RequestMapping("/protal")
   public String index() {
       return "protal";
   }

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value = "/systemUser/query")
    @ResponseBody
    public List<SystemUser> getSystemUser(HttpServletRequest request,@ApiParam("请求对象") @RequestParam Map<String, Object> params) {
        return service.findOneWithAuthoritiesByLogin();
    }
}
