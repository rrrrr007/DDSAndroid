package com.diucity.chandroid.ui.activity;

import android.content.Intent;
import android.os.Handler;

import com.diucity.chandroid.R;
import com.diucity.chandroid.base.BaseActivity;
import com.diucity.chandroid.ui.event.MainEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SplashActivity extends BaseActivity {


    @Override
    protected void init() {
        skip();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initInject() {
        //初始化依赖注入
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_splash;
    }

    private void skip() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));

        }, 1000 * 3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(MainEvent event) {
        //startActivity(new Intent(this,SplashActivity.class));
    }
}
