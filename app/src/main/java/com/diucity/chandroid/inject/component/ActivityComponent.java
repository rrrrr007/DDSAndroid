package com.diucity.chandroid.inject.component;

import android.app.Activity;

import com.diucity.chandroid.inject.Scope.ActivityScope;
import com.diucity.chandroid.inject.module.ActivityModule;
import com.google.common.eventbus.Subscribe;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    Activity getActivity();
}


