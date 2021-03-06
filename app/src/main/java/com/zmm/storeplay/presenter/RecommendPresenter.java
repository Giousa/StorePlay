package com.zmm.storeplay.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.zmm.storeplay.bean.BaseBean;
import com.zmm.storeplay.bean.IndexBean;
import com.zmm.storeplay.common.rx.subscriber.ProgressSuscriber;
import com.zmm.storeplay.data.AppInfoModel;
import com.zmm.storeplay.presenter.contract.AppInfoContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午5:57
 */

public class RecommendPresenter extends BasePresenter<AppInfoModel,AppInfoContract.View> {


    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view) {
        super(model, view);
    }

//    public void requestDatas() {
//
//
//
//        mModel.getApps()
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new Observer<PageBean<AppInfo>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        mView.showLoading();
//                    }
//
//                    @Override
//                    public void onNext(PageBean<AppInfo> response) {
//
//                        System.out.println("response = "+response);
//                        if(response != null){
//                            List<AppInfo> appInfos = response.getDatas();
//
//                            mView.showResult(appInfos);
//                        }else {
//                            mView.showNodata();
//                        }
//
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mView.dismissLoading();
//                    }
//                });
//
//
//    }


//    public void requestDatas() {
//
//        mModel.getApps()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PageBean<AppInfo>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        mView.showLoading();
//                    }
//
//                    @Override
//                    public void onNext(PageBean<AppInfo> response) {
//                        if(response != null){
//                            List<AppInfo> appInfos = response.getDatas();
//
//                            mView.showResult(appInfos);
//                        }else {
//                            mView.showNodata();
//                        }
//
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mView.dismissLoading();
//                    }
//                });
//
//
//    }

    public void requestDatas() {

        Activity activity = null;

        if(mView instanceof Fragment){
            activity = ((Fragment) mView).getActivity();
        }else if(mView instanceof Activity){
            activity = (Activity) mView;
        }

//        mModel.getApps()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ProgressDialogSuscriber<PageBean<AppInfo>>(activity) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> response) {
//                        if(response != null){
//                            List<AppInfo> appInfos = response.getDatas();
//
//                            mView.showResult(appInfos);
//                        }else {
//                            mView.showNodata();
//                        }
//
//                        mView.dismissLoading();
//                    }
//
//                    //是否显示dialog
//                    @Override
//                    protected boolean isShowDialog() {
//                        return true;
//                    }
//                });

//        mModel.getApps()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ProgressSuscriber<PageBean<AppInfo>>(mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> response) {
//                        if(response != null){
//                            List<AppInfo> appInfos = response.getDatas();
//
//                            mView.showResult(appInfos);
//                        }else {
//                            mView.showNodata();
//                        }
//
//                        mView.dismissLoading();
//                    }
//                });


//        RxPermissions rxPermissions = new RxPermissions(activity);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .flatMap(new Function<Boolean, Observable<PageBean<AppInfo>>>(){
//
//                    @Override
//                    public Observable<PageBean<AppInfo>> apply(Boolean aBoolean) throws Exception {
//
//                        System.out.println("aBoolean = "+aBoolean);
//                        if(aBoolean){
//                            return mModel.getApps().subscribeOn(Schedulers.io());
//                        }else {
//                            return Observable.empty();
//                        }
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ProgressSuscriber<PageBean<AppInfo>>(mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> response) {
//
//                        System.out.println("response = "+response);
//                        if(response != null){
//                            List<AppInfo> appInfos = response.getDatas();
//                            System.out.println("appInfos = "+appInfos);
//
//                            mView.showResult(appInfos);
//                        }else {
//                            mView.showNodata();
//                        }
//
//                        mView.dismissLoading();
//                    }
//                });

        mModel.index()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSuscriber<BaseBean<IndexBean>>(mView) {

                    @Override
                    public void onNext(BaseBean<IndexBean> value) {
                        if(value != null){
                            IndexBean indexBean = value.getData();
                            if(indexBean != null){
                                mView.showResult(indexBean);
                            }else {
                                mView.showNodata();
                            }
                        }else {
                            mView.showNodata();
                        }
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
