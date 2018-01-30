package com.zmm.storeplay;

import android.app.Application;
import android.content.Context;

import com.zmm.storeplay.di.component.AppComponent;
import com.zmm.storeplay.di.component.DaggerAppComponent;
import com.zmm.storeplay.di.module.AppModule;
import com.zmm.storeplay.di.module.HttpModule;


/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午9:50
 */

public class AppApplication extends Application{

    private AppComponent mAppComponent;

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();

    }
}
