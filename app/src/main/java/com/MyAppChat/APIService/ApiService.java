package com.MyAppChat.APIService;


import com.MyAppChat.Utils.ListFriendResponse;
import com.MyAppChat.Utils.LoginResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.MyAppChat.Utils.RegisterRespone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
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
    @POST("user/register")
    @FormUrlEncoded
    Call<RegisterRespone> register(@Field("email") String email,
                                   @Field("gender") String gender,
                                   @Field("birthday") String birthday,
                                   @Field("password") String password,
                                   @Field("confirm_password") String confirm_password,
                                   @Field("first_name") String first_name,
                                   @Field("last_name") String lastname);

    @PATCH("user/profile/update")
    Call<ProfileResponse> updateProfile(@Field("gender") String gender,
                                        @Field("birthday") String birthday,
                                        @Field("first_name") String first_name,
                                        @Field("last_name") String lastname);

}
