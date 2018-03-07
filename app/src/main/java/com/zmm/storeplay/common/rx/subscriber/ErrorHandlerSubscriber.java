package com.zmm.storeplay.common.rx.subscriber;

import android.content.Context;
import android.util.Log;

import com.zmm.storeplay.common.exception.BaseException;
import com.zmm.storeplay.common.rx.RxErrorHandler;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/3/7
 * Time:下午10:53
 */

public abstract  class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {


    protected RxErrorHandler mErrorHandler = null;

    protected Context mContext;

    public ErrorHandlerSubscriber(Context context){

        this.mContext = context;


        mErrorHandler = new RxErrorHandler(mContext);

    }


    @Override
    public void onError(Throwable e) {

        BaseException baseException =  mErrorHandler.handleError(e);

        if(baseException==null){
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber",e.getMessage());
        }
        else {
            mErrorHandler.showErrorMessage(baseException);
        }

    }


}