package com.dxy.zhbean.globalexception.base;

import com.dxy.zhbean.globalexception.dto.ResponseData;
import com.dxy.zhbean.globalexception.exception.CustomsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/8/27
 */
@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(value = Exception.class)
    public ResponseData defaultExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        System.out.println("-------------GlobalExceptionHandler.defaultExceptionHandler----------");
        return ResponseData.error(400, e.getMessage());
    }

    @ExceptionHandler(value = CustomsException.class)
    public ResponseData customsExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        CustomsException exception = (CustomsException) e;
        System.out.println("-------------GlobalExceptionHandler.customsExceptionHandler----------");
        return ResponseData.error(exception.getCode(), e.getMessage());
    }

}
