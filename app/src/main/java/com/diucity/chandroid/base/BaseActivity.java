package com.diucity.chandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diucity.chandroid.base.mvp.Delegate;
import com.zhy.autolayout.AutoLayoutActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity<D extends Delegate> extends AutoLayoutActivity {
    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    public D delegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //透明状态栏
        setContentView(layoutId());
        super.onCreate(savedInstanceState);
        initInject();
        ButterKnife.bind(this);
        init();
    }

    protected abstract void init();

    protected abstract void initInject();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (delegate != null) {
            delegate.onDestroy();
        }
    }
}
