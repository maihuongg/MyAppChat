package com.MyAppChat.APIService;


import com.MyAppChat.Utils.ListFriendResponse;
import com.MyAppChat.Utils.LoginResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("user/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String email,
                        @Field("password") String password);

    @GET("user/profile/{id}")
    Call<ProfileResponse> getProfile(@Path("id") int id);

    @GET("friend/list/{id}")
    Call<List<ListFriendResponse>> getFriendList(@Path("id") int id);
}
