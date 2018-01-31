package com.zmm.storeplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater2;
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
 * Time:上午10:33
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    private Unbinder mBind;

    private AppApplication mAppApplication;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));

        super.onCreate(savedInstanceState);

        setContentView(setLayout());

        mBind = ButterKnife.bind(this);
        this.mAppApplication = (AppApplication) getApplication();

        setupActivityComponent(mAppApplication.getAppComponent());

        init();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mBind != Unbinder.EMPTY){
            mBind.unbind();
        }
    }

    protected abstract int setLayout();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract void init();



}
