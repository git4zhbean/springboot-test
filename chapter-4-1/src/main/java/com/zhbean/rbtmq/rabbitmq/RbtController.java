package com.zhbean.rbtmq.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/11/14
 */
@RestController
public class RbtController {

    @Autowired
    private Sender sender;

    @GetMapping("/test")
    public String rbtmq(@RequestParam("num") int num) {
        String msg = "some text for rabbitmq test ";
        for (int i = 1; i < num; i++) {
            String context = msg +"--------"+ i;
            sender.send(context);
        }

        return "rabbitmq test success";
    }


}
