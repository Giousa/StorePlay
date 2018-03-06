package com.zmm.storeplay.presenter.contract;

import com.zmm.storeplay.bean.IndexBean;
import com.zmm.storeplay.ui.BaseView;


/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/1/30
 * Time:下午5:52
 */

public interface RecomendContract {

    interface View extends BaseView {
        void showResult(IndexBean indexBean);
        void showNodata();
        void showError();
    }

}
