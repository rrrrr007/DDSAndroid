package com.diucity.chandroid.api;

import com.diucity.chandroid.entity.SystemBack;
import com.diucity.chandroid.entity.SystemBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("recycler.notices")
    Observable<SystemBack> notices(@Query("sign") String sign, @Body SystemBean bean);
}

