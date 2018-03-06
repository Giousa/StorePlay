package com.zmm.storeplay.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zmm.storeplay.R;
import com.zmm.storeplay.bean.IndexBean;
import com.zmm.storeplay.di.component.AppComponent;
import com.zmm.storeplay.di.component.DaggerRecommendComponent;
import com.zmm.storeplay.di.module.RecommendModule;
import com.zmm.storeplay.presenter.RecommendPresenter;
import com.zmm.storeplay.presenter.contract.RecomendContract;
import com.zmm.storeplay.ui.adapter.IndexMultipleAdapter;
import com.zmm.storeplay.ui.decoration.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecomendContract.View{


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;



    private IndexMultipleAdapter mAdatper;

    @Inject
    ProgressDialog mProgressDialog;


    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }



    @Override
    public void init() {

        initRecycleView();
        mPresenter.requestDatas();
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .recommendModule(new RecommendModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }



    private void initRecycleView() {
        //为RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

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
    public void showResult(IndexBean indexBean) {

        mAdatper = new IndexMultipleAdapter(getActivity());
        mAdatper.setData(indexBean);

        mRecyclerView.setAdapter(mAdatper);

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
