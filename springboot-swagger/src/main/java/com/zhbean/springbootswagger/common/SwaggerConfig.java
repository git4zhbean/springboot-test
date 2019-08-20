package com.zhbean.springbootswagger.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/8/20 10:18
 */
@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    public static final String BASE_PACKAGE = "com.zhbean.springbootswagger";
    public static final String API_VERSION = "1.0.0";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .termsOfServiceUrl("https://xxx.com")
                .description("HTTP对外开放接口")
                .license("license")
                .licenseUrl("https://xxx.com")
                .title("XX项目接口文档")
                .version(API_VERSION)
                .build();
    }
}
