package com.MyAppChat.APIService;


import com.MyAppChat.Model.UserModel;
import com.MyAppChat.Utils.LoginResponse;
import com.MyAppChat.Utils.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    public static final String BASE_URL_user = "http:/localhost:8000/user/";

    //    @GET("/user/login")
//    Call<ResponseBody> login();
    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("username") String username,
                        @Field("password") String password);

    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    ApiService serviceAPI = new Retrofit.Builder()
            .baseUrl(BASE_URL_user)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


}
