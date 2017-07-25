package com.diucity.newsoulrecycler.api;

import android.content.Context;

import com.diucity.newsoulrecycler.utils.LogUtils;
import com.diucity.newsoulrecycler.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class Callback<T> implements Observer<T> {

    private Context mContext;

    public Callback() {

    }

    public Callback(Context context) {
        this.mContext = context;
    }


    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException) {
            ToastUtils.showShort("当前网络不可用，请检查网络后重试");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtils.showShort("请求超时，请稍后重试");
        } else if (e instanceof HttpException) {
            ToastUtils.showShort("服务器繁忙");
        } else {
            ToastUtils.showShort("服务器繁忙");
        }

        LogUtils.e("出错了大兄弟 -------------- " + e.toString());
        onFailure();
    }

    @Override
    public void onSubscribe(Disposable d) {
        subscribe(d);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }


    protected abstract void subscribe(Disposable d);

    protected abstract void onFailure();

    protected abstract void onSuccess(T t);
}
