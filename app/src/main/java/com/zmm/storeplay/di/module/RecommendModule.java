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
public class RecommendModule {

    private AppInfoContract.View mView;

    public RecommendModule(AppInfoContract.View view) {
        this.mView = view;
    }

    @Provides
    public AppInfoContract.View provideRecommendView(){

        return mView;
    }


    @Provides
    public RecommendPresenter provideRecommendPresenter(AppInfoModel recommendModel, AppInfoContract.View view){
        return new RecommendPresenter(recommendModel,view);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }

    @Provides
    public AppInfoModel provideRecommendModel(ApiService apiService){
        return new AppInfoModel(apiService);
    }
}
