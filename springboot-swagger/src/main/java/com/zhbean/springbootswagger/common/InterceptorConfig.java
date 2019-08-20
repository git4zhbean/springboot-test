package com.zhbean.springbootswagger.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/8/20 10:18
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * swagger增加url映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/doc/**").addResourceLocations("classpath:/swagger/dist/");
    }

}
