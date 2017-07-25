package com.diucity.newsoulrecycler.inject.component;

import android.support.v4.app.Fragment;

import com.diucity.newsoulrecycler.inject.Scope.FragmentScope;
import com.diucity.newsoulrecycler.inject.module.FragmentModule;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(Fragment fragment);
}
