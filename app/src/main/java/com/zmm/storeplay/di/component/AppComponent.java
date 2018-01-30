package com.zmm.storeplay.di.component;

import com.zmm.storeplay.data.http.ApiService;
import com.zmm.storeplay.di.module.AppModule;
import com.zmm.storeplay.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午9:51
 */


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();

}
