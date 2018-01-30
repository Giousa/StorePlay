package com.zmm.storeplay.data.http;

import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午9:06
 */

public class HttpManager {


    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }
}
