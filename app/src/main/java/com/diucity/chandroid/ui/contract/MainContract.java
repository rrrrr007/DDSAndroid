package com.diucity.chandroid.ui.contract;


import com.diucity.chandroid.base.mvp.IModel;
import com.diucity.chandroid.base.mvp.IView;
import com.diucity.chandroid.entity.SystemBack;
import com.diucity.chandroid.entity.SystemBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public interface MainContract {

    interface View extends IView {
        void hideLoading();

        void hideLoadingMore(boolean isFail);

        void setData(List<SystemBack.DataBean.NoticesBean> results);

        void addData(List<SystemBack.DataBean.NoticesBean> results);
    }

    interface Model extends IModel {
        Observable<SystemBack> getMainData(String sign, SystemBean bean);
    }
}
