package com.zmm.storeplay.di.component;

import com.zmm.storeplay.di.module.TopListModule;
import com.zmm.storeplay.di.scope.FragmentScope;
import com.zmm.storeplay.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/3/7
 * Time:下午9:28
 */


@FragmentScope
@Component(modules = {TopListModule.class},dependencies = AppComponent.class)
public interface TopListComponent {

    void inject(TopListFragment fragment);
}
