package com.zmm.storeplay.presenter;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.PageBean;
import com.zmm.storeplay.data.RecommendModel;
import com.zmm.storeplay.presenter.contract.RecomendContract;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午5:57
 */

public class RecommendPresenter implements RecomendContract.Presenter {


    private RecomendContract.View mView;


    private RecommendModel mRecommendModel;


    public RecommendPresenter(RecommendModel recommendModel,RecomendContract.View view) {
        mView = view;
        mRecommendModel = recommendModel;
    }

    @Override
    public void requestDatas() {

        mView.showLoading();

        mRecommendModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if(response != null){
                    List<AppInfo> appInfos = response.body().getDatas();

                    mView.showResult(appInfos);
                }else {
                    mView.showNodata();
                }

                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                t.printStackTrace();
                mView.dismissLoading();
                mView.showError();
            }
        });


    }
}