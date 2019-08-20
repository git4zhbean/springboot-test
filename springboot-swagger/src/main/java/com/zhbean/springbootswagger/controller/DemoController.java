package com.zhbean.springbootswagger.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/8/20 10:24
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(new HashMap<>().put("data", "demo"));
    }

}
