package com.zhbean.rbtmq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/11/14
 */
@Component
@RabbitListener(queues = "demo")
@Slf4j
public class Receiver {

    @RabbitHandler
    public void process(String msg) {
        String context = "Receiver : " + msg;
        log.info(context+" 已处理！Time: "+ new Date());
    }



}
