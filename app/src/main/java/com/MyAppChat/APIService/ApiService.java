package com.MyAppChat.APIService;

import com.MyAppChat.Utils.ChangePasswordResponse;
import com.MyAppChat.Utils.ListFriendResponse;
import com.MyAppChat.Utils.LoginResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.MyAppChat.Utils.RegisterRespone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ProfileResponse> updateProfile( @Body JSONObject obj,
                                        @Header("Authorization") String Authorization);


//    data class PasswordData(val String password);

    @POST("user/validate/password")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ChangePasswordResponse> updatePassword(@Header("Authorization") String Authorization,
                                                     @Body JSONObject obj);


}
