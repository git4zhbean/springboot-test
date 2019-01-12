package com.zhbean.rbtmq.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/11/14
 */
@Configuration
public class RbtConfig {

    @Bean
    public Queue queue() {
        return new Queue("demo");
    }

}
