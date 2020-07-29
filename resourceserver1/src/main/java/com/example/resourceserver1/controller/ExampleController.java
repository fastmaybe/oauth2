package com.example.resourceserver1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liulang
 * @Date: 2020/7/27 12:54
 */

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @RequestMapping("/hello")
    public String hello() {
        return "world";
    }

}
