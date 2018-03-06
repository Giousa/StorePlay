package com.zmm.storeplay.data.http;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.BaseBean;
import com.zmm.storeplay.bean.IndexBean;
import com.zmm.storeplay.bean.PageBean;
import com.zmm.storeplay.bean.requestbean.LoginRequestBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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


    @GET("index")
    public Observable<BaseBean<IndexBean>> index();


    @GET("toplist")
    public  Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

    @GET("game")
    public  Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);
}
