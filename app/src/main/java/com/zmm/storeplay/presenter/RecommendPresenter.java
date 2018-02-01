package com.zmm.storeplay.presenter;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.PageBean;
import com.zmm.storeplay.data.RecommendModel;
import com.zmm.storeplay.presenter.contract.RecomendContract;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午5:57
 */

public class RecommendPresenter extends BasePresenter<RecommendModel,RecomendContract.View> {


    public RecommendPresenter(RecommendModel model, RecomendContract.View view) {
        super(model, view);
    }


    public void requestDatas() {



        mModel.getApps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageBean<AppInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mView.showLoading();
            }

            @Override
            public void onNext(PageBean<AppInfo> response) {
                if(response != null){
                    List<AppInfo> appInfos = response.getDatas();

                    mView.showResult(appInfos);
                }else {
                    mView.showNodata();
                }

                mView.dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissLoading();
            }

            @Override
            public void onComplete() {
                mView.dismissLoading();
            }
        });


    }

//    public void requestDatas() {
//
//        mView.showLoading();
//
//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if(response != null){
//                    List<AppInfo> appInfos = response.body().getDatas();
//
//                    mView.showResult(appInfos);
//                }else {
//                    mView.showNodata();
//                }
//
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                t.printStackTrace();
//                mView.dismissLoading();
//                mView.showError();
//            }
//        });
//
//
//    }
}
