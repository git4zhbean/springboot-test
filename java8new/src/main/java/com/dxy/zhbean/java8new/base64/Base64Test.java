package com.dxy.zhbean.java8new.base64;

import java.util.Base64;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/6
 */
public class Base64Test {

    public static void main(String[] args){
        final String text = "Base64 in Java 8!";

        String encode = Base64.getEncoder().encodeToString(text.getBytes());
        System.out.println(encode);

        String decode = new String(Base64.getDecoder().decode(encode.getBytes()));
        System.out.println(decode);

    }



}
