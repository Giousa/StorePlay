package com.zmm.storeplay.presenter.contract;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.bean.IndexBean;
import com.zmm.storeplay.bean.PageBean;
import com.zmm.storeplay.ui.BaseView;


/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午5:52
 */

public interface AppInfoContract {

    interface View extends BaseView {
        void showResult(IndexBean indexBean);
        void showNodata();
        void showError();
    }


    interface TopListView extends BaseView{
        void showResult(PageBean<AppInfo> data);

        void onLoadMoreComplete();

    }
}
