package com.diucity.chandroid.ui.model;

import com.diucity.chandroid.api.ApiManager;
import com.diucity.chandroid.base.mvp.BaseModel;
import com.diucity.chandroid.entity.SystemBack;
import com.diucity.chandroid.entity.SystemBean;
import com.diucity.chandroid.ui.contract.MainContract;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class MainModel extends BaseModel implements MainContract.Model {

    private ApiManager apiManager;

    @Inject
    public MainModel(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public Observable<SystemBack> getMainData(String sign, SystemBean bean) {
        return apiManager.getApi().notices(sign, bean);
    }
}
