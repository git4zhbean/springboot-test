package com.zhbean.global_exception.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/8/27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {

    private int success = 1;
    private int code = 200;
    /**  响应时间  */
    private long responseTime = 0L;
    private String message;
    private Map<String, Object> data;
    private Long totalNum;

    public static ResponseData ok(Map<String, Object> data) {
        ResponseData r = new ResponseData(data);
        r.setMessage("请求成功");
        return r;
    }

    public static ResponseData ok(int code, String message) {
        ResponseData r = new ResponseData(null);
        r.setMessage(message);
        return r;
    }




    public static ResponseData error(int code, String message) {
        ResponseData r = new ResponseData();
        r.setSuccess(0);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static ResponseData parse(JSONObject json) {
        return JSON.toJavaObject(json, ResponseData.class);
    }

    public ResponseData(Map<String, Object> data) {
        super();
        this.data = data;
    }

    public ResponseData() {
        super();
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }



}
