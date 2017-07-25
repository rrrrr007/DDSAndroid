package com.diucity.newsoulrecycler.inject.module;

import com.diucity.newsoulrecycler.api.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ApiModule {

    @Singleton
    @Provides
    Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

}
