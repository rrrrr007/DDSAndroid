package com.diucity.newsoulrecycler.inject.component;

import com.diucity.newsoulrecycler.MyApplication;
import com.diucity.newsoulrecycler.api.ApiManager;
import com.diucity.newsoulrecycler.inject.module.ApiModule;
import com.diucity.newsoulrecycler.inject.module.AppModule;
import com.diucity.newsoulrecycler.inject.module.HttpModule;

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
