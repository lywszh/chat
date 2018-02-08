package com.kinoymir.chat.common;

import java.time.LocalDateTime;

import lombok.Data;

@Data
/**
 * json返回结果
 *
 * @author kino
 */
public class JsonResult {
    private boolean result =true ;//返回结果 。ture or flase
    private int code;//状态码(状态码与信息参见 接口文档)
    private String message;//附带的信息(主要为状态码的描述信息)
    private String time = LocalDateTime.now().toString();//此时的系统时间
    private String data;//携带的信息
    private Object dataObj;//携带的对象

    public JsonResult success(){
        this.result=true;
        this.code=0;
        this.message="请求成功";
        return this;
    }

    public JsonResult failed(String message){
        this.result=false;
        this.code=500;
        this.message=message;
        return this;
    }


    public JsonResult data(String data) {
        this.data = data;
        return this;
    }

    public JsonResult dataObj(Object data) {
        this.dataObj = data;
        return this;
    }
}
