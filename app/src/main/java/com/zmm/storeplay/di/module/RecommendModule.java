package com.zmm.storeplay.di.module;

import android.app.ProgressDialog;

import com.zmm.storeplay.data.RecommendModel;
import com.zmm.storeplay.data.http.ApiService;
import com.zmm.storeplay.presenter.RecommendPresenter;
import com.zmm.storeplay.presenter.contract.RecomendContract;
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

    private RecomendContract.View mView;

    public RecommendModule(RecomendContract.View view) {
        this.mView = view;
    }

    @Provides
    public RecomendContract.View provideRecommendView(){

        return mView;
    }


    @Provides
    public RecommendPresenter provideRecommendPresenter(RecommendModel recommendModel,RecomendContract.View view){
        return new RecommendPresenter(recommendModel,view);
    }

    @Provides
    public ProgressDialog provideProgressDialog(RecomendContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }

    @Provides
    public RecommendModel provideRecommendModel(ApiService apiService){
        return new RecommendModel(apiService);
    }
}
