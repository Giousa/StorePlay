package com.zmm.storeplay.common.exception;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/2/1
 * Time:下午10:20
 */

public class ApiException extends BaseException {


    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
