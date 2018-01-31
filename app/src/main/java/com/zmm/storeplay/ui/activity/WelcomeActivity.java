package com.zmm.storeplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.zmm.storeplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/31
 * Time:上午11:11
 */

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.pathView)
    PathView mPathView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mPathView.getPathAnimator()
                .delay(100)
                .duration(3000)
                .interpolator(new AccelerateDecelerateInterpolator())
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        finish();

                    }
                })
                .start();
    }
}
