package com.example.outhserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: liulang
 * @Date: 2020/8/21 18:24
 */
@RestController("/")
public class DemoController {


    @RequestMapping("hello2")
    public String hello(HttpServletRequest request){
        return  request.getRemoteHost();
    }
}
