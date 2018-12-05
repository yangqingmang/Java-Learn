package com.qmyang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * helloworld controller
 *
 * @author yangqingmang
 * @version 1.0
 * @date Create in 2018-12-05 11:34
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }

}
