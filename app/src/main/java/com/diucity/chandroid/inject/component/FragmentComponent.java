package com.diucity.chandroid.inject.component;

import android.app.Activity;

import com.diucity.chandroid.inject.Scope.FragmentScope;
import com.diucity.chandroid.inject.module.FragmentModule;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
    Activity getActivity();

}
