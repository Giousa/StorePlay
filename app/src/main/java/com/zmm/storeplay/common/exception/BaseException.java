package com.zmm.storeplay.common.exception;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/2/1
 * Time:下午10:19
 */

public class BaseException extends Exception{

    private int code;

    private String displayMessage;

    public BaseException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
