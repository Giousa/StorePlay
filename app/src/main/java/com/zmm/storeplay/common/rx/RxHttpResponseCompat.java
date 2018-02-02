package com.zmm.storeplay.common.rx;


import com.zmm.storeplay.bean.BaseBean;
import com.zmm.storeplay.common.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/2/1
 * Time:下午10:13
 */

public class RxHttpResponseCompat {


    public static <T>ObservableTransformer<BaseBean<T>,T> compatResult(){


        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {

                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(final BaseBean<T> tBaseBean) throws Exception {

                        System.out.println("tBaseBean message::"+tBaseBean.getMessage());
                        System.out.println("tBaseBean status::"+tBaseBean.getStatus());
                        System.out.println("tBaseBean data::"+tBaseBean.getData());

                        if(tBaseBean.isSuccess()){

                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> e) throws Exception {

                                    System.out.println("Observable.create:"+tBaseBean.getData());
                                    try {
                                        e.onNext(tBaseBean.getData());
                                        e.onComplete();
                                    }catch (Exception ex){
                                        e.onError(ex);
                                    }

                                }
                            });

                        }else {
                            return Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }


//    public static <T> ObservableTransformer<BaseBean<T>,T> compatResult(){
//
//
//        return new ObservableTransformer<BaseBean<T>, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
//
//                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
//                    @Override
//                    public ObservableSource<T> apply(final BaseBean<T> tBaseBean) throws Exception {
//
//                        System.out.println("tBaseBean "+tBaseBean.getData());
//
//                        if(tBaseBean.isSuccess()){
//
//                            return Observable.create(new ObservableOnSubscribe<T>() {
//                                @Override
//                                public void subscribe(ObservableEmitter<T> e) throws Exception {
//
//                                    System.out.println("Observable.create:"+tBaseBean.getData());
//                                    try {
//                                        e.onNext(tBaseBean.getData());
//                                        e.onComplete();
//                                    }catch (Exception ex){
//                                        e.onError(ex);
//                                    }
//
//                                }
//                            });
//
//                        }else {
//                            return Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
//                        }
//
//                    }
//                }).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }
}
