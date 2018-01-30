package com.zmm.storeplay.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午9:50
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){

        return mApplication;
    }
}
