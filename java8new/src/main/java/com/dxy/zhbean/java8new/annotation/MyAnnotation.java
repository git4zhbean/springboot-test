package com.dxy.zhbean.java8new.annotation;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/6
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyLogAnnotation.class)
public @interface MyAnnotation {
    String value() default "";
}
