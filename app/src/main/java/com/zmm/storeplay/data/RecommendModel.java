package com.zmm.storeplay.data;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.PageBean;
import com.zmm.storeplay.data.http.ApiService;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import retrofit2.Callback;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午9:05
 */

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<PageBean<AppInfo>> getApps(){
//        HttpManager httpManager = new HttpManager();
//
//        httpManager.getRetrofit()
//                .create(ApiService.class)
//                .getApps("{'page':0}")
//                .enqueue(callback);

//        mApiService.getApps("{'page':0}").enqueue(callback);

        Observable<PageBean<AppInfo>> observable = mApiService.getApps("{'page':0}");
        return observable;
    }
}
