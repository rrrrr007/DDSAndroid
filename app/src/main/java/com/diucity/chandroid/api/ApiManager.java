package com.diucity.chandroid.api;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiManager {

    private final Api api;

    @Inject
    public ApiManager(Api api) {
        this.api = api;
    }

    public Api getApi() {
        return this.api;
    }

}
