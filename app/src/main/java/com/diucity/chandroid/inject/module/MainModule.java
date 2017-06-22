package com.diucity.chandroid.inject.module;

import com.diucity.chandroid.inject.Scope.ActivityScope;
import com.diucity.chandroid.ui.contract.MainContract;
import com.diucity.chandroid.ui.model.MainModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/22 0022.
 */
@Module
public class MainModule {
    private MainContract.View mView;

    public MainModule(MainContract.View view) {
        mView = view;
    }

    @Provides
    @ActivityScope
    MainContract.View provideView() {
        return mView;
    }

    @Provides
    @ActivityScope
    MainContract.Model provideModel(MainModel model) {
        return model;
    }
}
