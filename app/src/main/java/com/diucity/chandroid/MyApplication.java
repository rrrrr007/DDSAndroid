package com.diucity.chandroid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.diucity.chandroid.inject.component.AppComponent;
import com.diucity.chandroid.inject.component.DaggerAppComponent;
import com.diucity.chandroid.inject.module.ApiModule;
import com.diucity.chandroid.inject.module.AppModule;
import com.diucity.chandroid.inject.module.HttpModule;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private static MyApplication mInstance;
    private static List<Activity> activityList = new LinkedList();
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null) {
            mInstance = this;
        }
        mContext = getApplicationContext();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .apiModule(new ApiModule())
                .build();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                removeActivity(activity);
            }
        });
    }

    public static Context getContext() {
        return mContext;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    // 添加Activity到容器中
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 删除Activity
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    // 遍历所有Activity并finish
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
