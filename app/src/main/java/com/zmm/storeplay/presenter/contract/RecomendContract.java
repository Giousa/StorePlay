package com.zmm.storeplay.presenter.contract;

import com.zmm.storeplay.bean.AppInfo;
import com.zmm.storeplay.ui.BaseView;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午5:52
 */

public interface RecomendContract {

    interface View extends BaseView {
        void showResult(List<AppInfo> appInfos);
        void showNodata();
        void showError();
    }

}
