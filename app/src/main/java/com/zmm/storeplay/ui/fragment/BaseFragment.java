package com.zmm.storeplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmm.storeplay.AppApplication;
import com.zmm.storeplay.di.component.AppComponent;
import com.zmm.storeplay.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/31
 * Time:上午10:41
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private View mRootView;
    private Unbinder mBind;
    private AppApplication mAppApplication;

    @Inject
    T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(setLayout(),container,false);

        mBind = ButterKnife.bind(this,mRootView);

        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mAppApplication = (AppApplication) getActivity().getApplication();

        setupActivityComponent(mAppApplication.getAppComponent());

        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mBind != Unbinder.EMPTY){
            mBind.unbind();
        }
    }


    protected abstract int setLayout();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract void init();

}
