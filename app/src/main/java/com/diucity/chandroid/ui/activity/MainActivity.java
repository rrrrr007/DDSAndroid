package com.diucity.chandroid.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.diucity.chandroid.MyApplication;
import com.diucity.chandroid.R;
import com.diucity.chandroid.base.BaseActivity;
import com.diucity.chandroid.entity.SystemBack;
import com.diucity.chandroid.entity.SystemBean;
import com.diucity.chandroid.inject.component.DaggerMainComponent;
import com.diucity.chandroid.inject.module.MainModule;
import com.diucity.chandroid.ui.contract.MainContract;
import com.diucity.chandroid.ui.delegate.MainDelegate;
import com.diucity.chandroid.ui.event.MainEvent;
import com.diucity.chandroid.utils.GsonUtils;
import com.diucity.chandroid.utils.SignUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainDelegate> implements MainContract.View {

    @BindView(R.id.rv_main)
    public RecyclerView rv;

    @Override
    protected void init() {
        SystemBean bean = new SystemBean(100005, "d6fe7d55-8aac-47c8-bd27-abc96ad60202", -1, 10);
        delegate.getMainDatas(SignUtils.sign(GsonUtils.serialize(bean)), bean);
    }

    @Override
    protected void initInject() {
        DaggerMainComponent.builder().appComponent(((MyApplication) getApplication()).getAppComponent())
                .mainModule(new MainModule(this)).build().injectMain(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void hideLoadingMore(boolean isFail) {

    }

    @Override
    public void setData(List<SystemBack.DataBean.NoticesBean> results) {

    }

    @Override
    public void addData(List<SystemBack.DataBean.NoticesBean> results) {

    }


}
