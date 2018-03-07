package com.zmm.storeplay.di.module;

import android.app.ProgressDialog;

import com.zmm.storeplay.data.AppInfoModel;
import com.zmm.storeplay.data.http.ApiService;
import com.zmm.storeplay.presenter.RecommendPresenter;
import com.zmm.storeplay.presenter.contract.AppInfoContract;
import com.zmm.storeplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午9:21
 */

@Module
public class TopListModule {

    private AppInfoContract.TopListView mView;

    public TopListModule(AppInfoContract.TopListView view) {
        this.mView = view;
    }

    @Provides
    public AppInfoContract.TopListView provideView(){

        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }

}
