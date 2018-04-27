package com.itl.iap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Joeysin on 2017/1/31.
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
    @GetMapping("/demo")
    public String user(){
        return "测试开发的demo";
    }
}
