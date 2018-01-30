package com.zmm.storeplay.di.component;

import com.zmm.storeplay.di.module.RecommendModule;
import com.zmm.storeplay.di.scope.FragmentScope;
import com.zmm.storeplay.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午9:28
 */
@FragmentScope
@Component(modules = {RecommendModule.class},dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
