package com.zmm.storeplay.data.http;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.PageBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:上午11:03
 */

public interface ApiService {

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured")
    public Observable<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);
}
