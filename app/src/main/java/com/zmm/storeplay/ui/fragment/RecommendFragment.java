package com.zmm.storeplay.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zmm.storeplay.AppApplication;
import com.zmm.storeplay.R;
import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.di.component.AppComponent;
import com.zmm.storeplay.di.component.DaggerRecommendComponent;
import com.zmm.storeplay.di.module.RecommendModule;
import com.zmm.storeplay.presenter.RecommendPresenter;
import com.zmm.storeplay.presenter.contract.RecomendContract;
import com.zmm.storeplay.ui.adapter.RecomendAppAdatper;
import com.zmm.storeplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecomendContract.View{


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;



    private RecomendAppAdatper mRecomendAppAdatper;

    @Inject
    ProgressDialog mProgressDialog;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recomend;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .recommendModule(new RecommendModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void init() {
        mPresenter.requestDatas();
    }


    private void initRecyclerView(List<AppInfo> datas) {
        //为RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecomendAppAdatper = new RecomendAppAdatper(getActivity(), datas);

        mRecyclerView.setAdapter(mRecomendAppAdatper);

    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showResult(List<AppInfo> appInfos) {
        initRecyclerView(appInfos);
    }

    @Override
    public void showNodata() {
        Toast.makeText(getActivity(),"暂时无数据，请吃完饭再来", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(),"服务器开小差了",Toast.LENGTH_LONG).show();
    }
}
