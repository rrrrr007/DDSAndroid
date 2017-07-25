package com.diucity.newsoulrecycler.api;

import com.diucity.newsoulrecycler.entity.NormalBack;
import com.diucity.newsoulrecycler.entity.SystemBack;
import com.diucity.newsoulrecycler.entity.SystemBean;
import com.diucity.newsoulrecycler.entity.UpLoadBack;
import com.diucity.newsoulrecycler.entity.UpLoadBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {

    /*@POST("recycler.notices")
    Observable<SystemBack> notices(@Query("sign") String sign, @Body SystemBean bean);*/

    @POST("collect/collectdata")
    Observable<UpLoadBack> upload(@QueryMap Map<String, String> options);
}

