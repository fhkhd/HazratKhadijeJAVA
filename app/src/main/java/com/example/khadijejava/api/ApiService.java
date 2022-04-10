package com.example.khadijejava.api;

import retrofit2.Call;
import retrofit2.http.GET;
import com.example.khadijejava.model.Data;

public interface ApiService {

    @GET("posts")
    Call<Data> getPost();

}


