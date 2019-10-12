package com.zhbean.rwsplitting.bean;

import lombok.Data;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/8/14 11:12
 */
@Data
public class ResponseData {

    private int success = 1;
    private int code = 200;
    /** 响应时间 */
    private long responseTime = 0L;
    private String message;
    private Map<String, Object> data;

    public ResponseData(Map<String,Object> data) {
        super();
        this.data = data;
    }
    public ResponseData(){}

    public static ResponseData ok(Map<String, Object> data, long totalTime) {
        ResponseData responseData = new ResponseData(data);
        responseData.setMessage("请求成功");
        responseData.setResponseTime(totalTime);

        return responseData;
    }

    public static ResponseData error(int code, String message, long totalTime) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(code);
        responseData.setMessage(message);
        responseData.setSuccess(0);
        responseData.setResponseTime(totalTime);

        return responseData;
    }

}
