package com.diucity.chandroid.inject.component;

import com.diucity.chandroid.MyApplication;
import com.diucity.chandroid.api.ApiManager;
import com.diucity.chandroid.inject.module.ApiModule;
import com.diucity.chandroid.inject.module.AppModule;
import com.diucity.chandroid.inject.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = {AppModule.class, ApiModule.class, HttpModule.class})
public interface AppComponent {
    void inject(MyApplication application);

    //application
    MyApplication application();

    //retrofit
    Retrofit retrofit();

    //服务管理器,retrofitApi
    ApiManager apiManager();

}
