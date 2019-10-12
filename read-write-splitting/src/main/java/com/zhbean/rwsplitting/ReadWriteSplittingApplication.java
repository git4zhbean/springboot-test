package com.zhbean.rwsplitting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = {"com.zhbean.rwsplitting.mapper"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.zhbean.rwsplitting"})
public class ReadWriteSplittingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadWriteSplittingApplication.class, args);
    }


}
