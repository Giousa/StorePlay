package com.zmm.storeplay.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

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

    protected Context mContext;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;

        initContext();
    }

    private void initContext(){



        if(mView instanceof Fragment){
            mContext = ((Fragment)mView).getActivity();
        }
        else {
            mContext = (Activity) mView;
        }
    }
}
