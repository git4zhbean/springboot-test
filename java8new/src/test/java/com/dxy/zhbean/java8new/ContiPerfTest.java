package com.dxy.zhbean.java8new;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Java8newApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContiPerfTest {

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 1000, threads = 40)
    public void test1() {
//        Thread.sleep(200);
        String msg = "This is a test";
        String result = process(msg);

        Assert.assertEquals(msg, result);
    }


    public String process(String msg) {
        return msg;
    }
}
