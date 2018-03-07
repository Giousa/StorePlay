package com.zmm.storeplay.presenter;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.BaseBean;
import com.zmm.storeplay.bean.PageBean;
import com.zmm.storeplay.common.rx.subscriber.ErrorHandlerSubscriber;
import com.zmm.storeplay.common.rx.subscriber.ProgressSuscriber;
import com.zmm.storeplay.data.AppInfoModel;
import com.zmm.storeplay.presenter.contract.AppInfoContract;

import org.reactivestreams.Subscriber;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/3/7
 * Time:下午9:13
 */

public class TopListPresenter extends BasePresenter<AppInfoModel,AppInfoContract.TopListView> {


    @Inject
    public TopListPresenter(AppInfoModel model, AppInfoContract.TopListView view) {
        super(model, view);
    }


    public void getTopListApps(int page){

        System.out.println("page = "+page);

        Observer observer;

        if(page == 0){
            //第一页，显示loading
            observer = new ProgressSuscriber<BaseBean<PageBean<AppInfo>>>(mView) {
                @Override
                public void onNext(BaseBean<PageBean<AppInfo>> value) {

                    if(value.isSuccess()){
                        PageBean<AppInfo> data = value.getData();
                        mView.showResult(data);

                    }else {

                    }

                }
            };
        }else {

            //加载下一页
            observer = new ErrorHandlerSubscriber<BaseBean<PageBean<AppInfo>>>(mContext) {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(BaseBean<PageBean<AppInfo>> value) {
                    mView.showResult(value.getData());
                }

                @Override
                public void onComplete() {

                    mView.onLoadMoreComplete();

                }
            };

        }


        mModel.topList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
