package com.example.demo.util;



import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult implements Serializable{

    private int code;   //返回码 非0即失败
    private String msg; //消息提示
    private Object data; //返回的数据

    public static String success() {
        return success(null);
    }
    public static String success(Object data) {
        return JSON.toJSONString(new JsonResult(0, "解析成功", data));
    }

    public static String failed() {
        return failed("解析失败");
    }
    public static String failed(String msg) {
        return failed(-1, msg);
    }
    public static String failed(int code, String msg) {
        return JSON.toJSONString(new JsonResult(code, msg, null));
    }

}

