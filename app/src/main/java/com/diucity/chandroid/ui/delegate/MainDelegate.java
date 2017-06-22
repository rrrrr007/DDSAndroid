package com.diucity.chandroid.ui.delegate;

import android.util.Log;

import com.diucity.chandroid.api.Callback;
import com.diucity.chandroid.base.mvp.Delegate;
import com.diucity.chandroid.entity.SystemBack;
import com.diucity.chandroid.entity.SystemBean;
import com.diucity.chandroid.ui.contract.MainContract;
import com.diucity.chandroid.ui.event.MainEvent;
import com.diucity.chandroid.ui.model.MainModel;
import com.diucity.chandroid.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class MainDelegate extends Delegate<MainModel, MainContract.View> {
    @Inject
    public MainDelegate(MainModel model, MainContract.View view) {
        super(model, view);
    }

    public void getMainDatas(String sign, SystemBean bean) {
        invoke(mModel.getMainData(sign, bean), new Callback<SystemBack>() {
            @Override
            protected void subscribe(Disposable d) {

            }

            @Override
            protected void onFailure() {

            }

            @Override
            protected void onSuccess(SystemBack systemBack) {
                Log.d("ch", "" + GsonUtils.serialize(systemBack));
                EventBus.getDefault().post(new MainEvent("ch"));
            }
        });

    }

}
