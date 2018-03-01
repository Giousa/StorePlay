package com.zmm.storeplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zmm.storeplay.AppApplication;
import com.zmm.storeplay.R;
import com.zmm.storeplay.di.component.AppComponent;
import com.zmm.storeplay.presenter.BasePresenter;
import com.zmm.storeplay.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/3/1
 * Time:下午4:58
 */

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private FrameLayout mRootView;
    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private TextView mTextError;
    private AppApplication mApplication;
    private Unbinder mUnbinder;

    @Inject
    T mPresenter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress,container,false);

        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewContent = mRootView.findViewById(R.id.view_content);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);

        mTextError = mRootView.findViewById(R.id.text_tip);

        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmptyViewClick();
            }
        });


        return mRootView;
    }

    public void onEmptyViewClick() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mApplication = (AppApplication) getActivity().getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());
        setRealContentView();

        init();

    }

    private void setRealContentView() {

        View realContentView=  LayoutInflater.from(getActivity()).inflate(setLayout(),mViewContent,true);
        mUnbinder=  ButterKnife.bind(this, realContentView);


    }

    @Override
    public void showLoading() {
        showProgressView();
    }


    @Override
    public void showError(String msg) {
        showEmptyView(msg);

    }


    @Override
    public void dismissLoading() {
        showContentView();

    }

    public void showProgressView() {
        showView(R.id.view_progress);
    }


    public void showContentView() {
        showView(R.id.view_content);
    }

    public void showEmptyView() {
        showView(R.id.view_empty);
    }

    public void showEmptyView(String msg) {
        showEmptyView();
        mTextError.setText(msg);
    }

    private void showEmptyView(int resId) {
        showEmptyView();
        mTextError.setText(resId);
    }




    private void showView(int viewId){
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            if(mRootView.getChildAt(i).getId() == viewId){
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            }else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    public abstract void  init();

    public abstract int setLayout();

    public abstract  void setupAcitivtyComponent(AppComponent appComponent);
}
