package com.zhbean.rbtmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhbean
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RbtMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbtMqApplication.class, args);
	}
}
