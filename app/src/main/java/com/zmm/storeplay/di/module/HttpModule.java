package com.zmm.storeplay.di.module;

import android.app.Application;

import com.zmm.storeplay.common.http.CommonParamsInterceptor;
import com.zmm.storeplay.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午9:33
 */

@Module
public class HttpModule {


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application){



        // log用拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //自定义的公共参数
        CommonParamsInterceptor commonParamsInterceptor = new CommonParamsInterceptor(application);

        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
//        SSLSocketFactory sslSocketFactory = null;

        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
//                .addInterceptor(new HeadInterceptor())
                .addInterceptor(logging)
                .addInterceptor(commonParamsInterceptor)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)

                .build();


    }

//    @Provides
//    @Singleton
//    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
//        return new Retrofit.Builder()
//                .baseUrl(ApiService.BASE_URL)
//                .addConverterFactory(FastJsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);


        return builder.build();

    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

}
