package com.zmm.storeplay.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zmm.storeplay.R;
import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.PageBean;
import com.zmm.storeplay.di.component.AppComponent;
import com.zmm.storeplay.di.component.DaggerTopListComponent;
import com.zmm.storeplay.di.module.TopListModule;
import com.zmm.storeplay.presenter.TopListPresenter;
import com.zmm.storeplay.presenter.contract.AppInfoContract;
import com.zmm.storeplay.ui.adapter.AppInfoAdapter;
import com.zmm.storeplay.ui.widget.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TopListFragment extends ProgressFragment<TopListPresenter> implements AppInfoContract.TopListView, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    int page = 0;

    private AppInfoAdapter mAdapter;


    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }


    @Override
    public void init() {

        mPresenter.getTopListApps(page);

        initRecyclerView();
    }

    private void initRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = new AppInfoAdapter();

        //加载更多
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerTopListComponent
                .builder()
                .appComponent(appComponent)
                .topListModule(new TopListModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void showResult(PageBean<AppInfo> data) {
        mAdapter.addData(data.getDatas());
        if(data.isHasMore()){
            page++;
        }

        mAdapter.setEnableLoadMore(data.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getTopListApps(page);
    }
}
