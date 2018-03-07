package com.zmm.storeplay.data;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.BaseBean;
import com.zmm.storeplay.bean.IndexBean;
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

public class AppInfoModel {

    private ApiService mApiService;

    public AppInfoModel(ApiService apiService) {
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

        Observable<PageBean<AppInfo>> apps = mApiService.getApps("{'page':0}");
        return apps;
    }

    public Observable<BaseBean<IndexBean>> index(){

        return  mApiService.index();
    }


    public  Observable<BaseBean<PageBean<AppInfo>>> topList(int page){

        return  mApiService.topList(page);
    }

    public  Observable<BaseBean<PageBean<AppInfo>>> games(int page){

        return  mApiService.games(page);
    }
}
