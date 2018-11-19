package com.zhbean.global_exception.execption;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/8/27
 */
public class CustomsException extends RuntimeException {
    private int code;

    public CustomsException(){
       super();
   }

    public CustomsException(int code, String message) {
        super(message);
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
