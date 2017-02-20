package com.slkk.test_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by dell on 2017/2/16.
 */

public interface Api {
    @GET("/api/4/version/android/2.3.0")
    Call<DataBean> get();
}
