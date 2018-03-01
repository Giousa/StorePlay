package com.zmm.storeplay.ui;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午5:53
 */

public interface BaseView {

    void showLoading();
    void showError(String msg);
    void dismissLoading();
}
