package com.example.retrofit1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("api/users/{UserID}")
    Call<ResponseModel> getUser(@Path("UserID") int UserID);
}
