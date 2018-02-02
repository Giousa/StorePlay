package com.zmm.storeplay.bean;

import java.io.Serializable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/2/1
 * Time:下午10:05
 */

public class BaseBean<T> implements Serializable{


    public static final int SUCCESS = 1;

    private int status;
    private String message;
    private T data;

    public boolean isSuccess(){
        return (status == SUCCESS);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
