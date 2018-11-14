package com.zhbean.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/11/14
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String test() {
        return "hello world!";
    }


}
