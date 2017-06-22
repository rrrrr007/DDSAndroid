package com.diucity.chandroid.base.mvp;

import com.diucity.chandroid.api.Callback;
import com.diucity.chandroid.inject.Scope.ActivityScope;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ActivityScope
public class Delegate<M extends IModel, V extends IView> implements IPreseter {

    protected final String TAG = this.getClass().getSimpleName();
    protected M mModel;
    protected V mView;
    private CompositeDisposable mCompositeDisposable;

    public Delegate(M model, V view) {
        this.mModel = model;
        this.mView = view;
        onStart();
    }

    public Delegate(V rootView) {
        this.mView = rootView;
        onStart();
    }

    public Delegate() {
        onStart();
    }

    protected <T> void invoke(Observable<T> observable, Callback<T> callback) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);

    }

    @Override
    public void onStart() {
        if (useEventBus())
            EventBus.getDefault().register(this);//注册eventbus
    }

    @Override
    public void onDestroy() {
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this);//解除注册eventbus
        unSubscribe();//解除订阅
        if (mModel != null)
            mModel.onDestroy();
        this.mModel = null;
        this.mView = null;
        this.mCompositeDisposable = null;
    }

    protected boolean useEventBus() {
        return false;
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);//将所有subscription放入,集中处理
    }

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证activity结束时取消所有正在执行的订阅
        }
    }
}
