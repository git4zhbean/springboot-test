package com.dxy.zhbean.globalexception.controller;

import com.dxy.zhbean.globalexception.exception.CustomsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/8/27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/error")
    public String error(Integer num){
        if (num == null) {
            throw new CustomsException(10101, "参数num不能为空！");
        }

        int i = 5 / num;
        return "result : " + i;
    }


}
