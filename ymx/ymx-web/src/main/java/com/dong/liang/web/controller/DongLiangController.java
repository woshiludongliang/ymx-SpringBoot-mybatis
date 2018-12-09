package com.dong.liang.web.controller;

import com.dong.liang.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/test")
public class DongLiangController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String HelloWord(){

        String hello = helloService.helloMethod();

        return hello;
    }
}
