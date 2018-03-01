package com.zmm.storeplay.common.rx.subscriber;

import android.app.ProgressDialog;
import android.content.Context;

import com.zmm.storeplay.ui.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/2/1
 * Time:下午11:19
 */

public abstract class ProgressSuscriber<T> extends DefaultSubscriber<T> {


    private BaseView mView;

    public ProgressSuscriber(BaseView view) {
        this.mView = view;
    }

    public boolean isShowProgress(){
        return true;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(isShowProgress()){
            mView.showLoading();
        }
    }


    @Override
    public void onError(Throwable e) {
        mView.dismissLoading();
    }

    @Override
    public void onComplete() {
        mView.dismissLoading();
    }
}
