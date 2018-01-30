package com.zmm.storeplay.data;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.PageBean;
import com.zmm.storeplay.data.http.ApiService;

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

    public void getApps(Callback<PageBean<AppInfo>> callback){
//        HttpManager httpManager = new HttpManager();
//
//        httpManager.getRetrofit()
//                .create(ApiService.class)
//                .getApps("{'page':0}")
//                .enqueue(callback);

        mApiService.getApps("{'page':0}").enqueue(callback);
    }
}
