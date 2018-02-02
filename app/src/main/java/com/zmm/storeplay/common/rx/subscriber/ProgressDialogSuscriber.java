package com.zmm.storeplay.common.rx.subscriber;

import android.app.ProgressDialog;
import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/2/1
 * Time:下午11:19
 */

public abstract class ProgressDialogSuscriber<T> extends DefaultSubscriber<T> {

    private Context mContext;
    private ProgressDialog mDialog;

    public ProgressDialogSuscriber(Context context) {
        mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

        if(isShowDialog()){
            showProgressDialog();
        }
    }


    @Override
    public void onError(Throwable e) {
        if(isShowDialog()){
            dismissProgressDialog();
        }
    }



    @Override
    public void onComplete() {

        if(isShowDialog()){
            dismissProgressDialog();
        }
    }


    private void initProgressDialog(){

        if(mDialog == null){
            mDialog = new ProgressDialog(mContext);
            mDialog.setMessage("loading...");
        }


    }

    private void showProgressDialog() {
        initProgressDialog();
        mDialog.show();
    }


    private void dismissProgressDialog() {


        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    protected boolean isShowDialog(){
        return true;
    }
}
