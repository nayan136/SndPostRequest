package com.example.nayanjyoti.sndpostrequest.api.service;

import com.example.nayanjyoti.sndpostrequest.api.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<User> createAccount(@Body User user);
}
