package com.diucity.newsoulrecycler.inject.component;

import android.app.Activity;

import com.diucity.newsoulrecycler.inject.Scope.ActivityScope;
import com.diucity.newsoulrecycler.inject.module.ActivityModule;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(Activity activity);
}


