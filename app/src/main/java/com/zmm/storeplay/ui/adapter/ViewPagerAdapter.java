package com.zmm.storeplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zmm.storeplay.ui.bean.FragmentInfo;
import com.zmm.storeplay.ui.fragment.CategoryFragment;
import com.zmm.storeplay.ui.fragment.GamesFragment;
import com.zmm.storeplay.ui.fragment.RankingFragment;
import com.zmm.storeplay.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/21
 * Time:下午3:22
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private List<FragmentInfo> mFragmentInfos = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    private void initFragment() {
        mFragmentInfos.add(new FragmentInfo ("推荐",RecommendFragment.class));
        mFragmentInfos.add(new FragmentInfo ("排行", RankingFragment.class));
        mFragmentInfos.add(new FragmentInfo ("游戏", GamesFragment.class));
        mFragmentInfos.add(new FragmentInfo ("分类", CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragmentInfos.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mFragmentInfos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentInfos.get(position).getTitle();
    }
}
