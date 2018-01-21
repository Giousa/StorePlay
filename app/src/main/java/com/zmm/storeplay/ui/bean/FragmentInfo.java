package com.zmm.storeplay.ui.bean;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/21
 * Time:下午3:23
 */

public class FragmentInfo {

    private String title;

    private Class fragment;

    public FragmentInfo(String title, Class fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
