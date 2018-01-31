package com.zmm.storeplay.presenter;

import com.zmm.storeplay.ui.BaseView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午5:50
 */

public class BasePresenter<M,V extends BaseView> {

    protected M mModel;

    protected V mView;


    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }
}
