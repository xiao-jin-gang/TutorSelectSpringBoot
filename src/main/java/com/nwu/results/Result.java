package com.nwu.results;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private boolean success;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code, Object data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }

    public static String ok(int code, String message, Object data){
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);

        return JSON.toJSONString(result);
    }
    public static String ok(int code, String message){
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("message", message);

        return JSON.toJSONString(result);
    }

    public static String error(int code, String message){
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("message", message);

        return JSON.toJSONString(result);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
