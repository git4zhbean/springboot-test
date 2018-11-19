package com.zhbean.global_exception.base;

import com.zhbean.global_exception.http.HttpServletRequestWrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/5
 */
@Configuration
public class AppConfiguration {

    @Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> testFilterRegistration() {

        FilterRegistrationBean<HttpServletRequestWrapperFilter> registration = new FilterRegistrationBean<HttpServletRequestWrapperFilter>();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        //设置过滤路径
        registration.addUrlPatterns("/*");
        /* 添加默认参数 */
        registration.addInitParameter("name", "hand");
        //过滤器名字
        registration.setName("HttpServletRequestWrapperFilter");
        //设置优先级
        registration.setOrder(1);
        return registration;
    }



}
