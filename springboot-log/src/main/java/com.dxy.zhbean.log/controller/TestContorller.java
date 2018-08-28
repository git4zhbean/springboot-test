package com.dxy.zhbean.log.controller;

import com.dxy.zhbean.log.aop.CustomsLog;
import com.dxy.zhbean.log.aop.UserLogs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/8/28
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestContorller {

    @CustomsLog
    @GetMapping("/add")
    public String add(Integer num) {
        if (num == null) {
            return "参数num不能为空！";
        }
        int i = 5 + num;
        return "result : " + i;
    }

    @UserLogs
    @RequestMapping("/login")
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "pwd") String pwd) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)) {
//            return "用户名或密码不能为空！";
            return username + "登录失败!";
        }

        return username + "登录成功！";
    }

}
