package com.dxy.zhbean.java8new.contiperf;

import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/7
 */
@Service
public class UnitTestServiceImpl implements UnitTestService{
    @Override
    public String process(String msg) {
        return msg;
    }
}
