package com.diucity.chandroid.inject.component;


import com.diucity.chandroid.inject.Scope.ActivityScope;
import com.diucity.chandroid.inject.module.MainModule;
import com.diucity.chandroid.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

@ActivityScope
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {
    void injectMain(MainActivity activity);
}