package com.qmyang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello world
 *
 * @author yangqingmang
 * @version 1.0
 * @date Create in 2018-12-05 11:55
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String helloWorld() {
        return "hello world spring boot 2.0";
    }
}
