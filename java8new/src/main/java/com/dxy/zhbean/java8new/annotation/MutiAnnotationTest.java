package com.dxy.zhbean.java8new.annotation;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/6
 */
public class MutiAnnotationTest {

    @MyAnnotation("filter")
    @MyAnnotation("filters")
    @MyAnnotation("test1")
    public interface Filterable {
    }

    public static void main(String[] args) {
        MyAnnotation[] types = Filterable.class.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation myAnnotation : types) {
            System.out.println(myAnnotation.value());
        }
    }

}
