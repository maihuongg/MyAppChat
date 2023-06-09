package com.MyAppChat.APIService;

import com.MyAppChat.Model.AddFriendModel;
import com.MyAppChat.Model.ChatModel;
import com.MyAppChat.Model.CreateChatRoomModel;
import com.MyAppChat.Model.LastMessageModel;
import com.MyAppChat.Model.PasswordModel;
import com.MyAppChat.Model.SendMessageModel;
import com.MyAppChat.Model.UpdateChatRoomModel;
import com.MyAppChat.Model.UpdateProfileModal;
import com.MyAppChat.Utils.ChangePasswordResponse;
import com.MyAppChat.Utils.CreateChatRoomResponse;
import com.MyAppChat.Utils.DetailListFriendResponse;
import com.MyAppChat.Utils.ListFriendResponse;
import com.MyAppChat.Utils.LoginResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.MyAppChat.Utils.RegisterRespone;
import com.MyAppChat.Utils.RoomDetailResponse;
import com.MyAppChat.Utils.SendMessageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ProfileResponse> updateProfile(@Header("Authorization") String Authorization, @Body UpdateProfileModal body);


    @POST("user/validate/password")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ChangePasswordResponse> updatePassword(@Header("Authorization") String Authorization, @Body PasswordModel body);

    @GET("/chat/room/list")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<List<ChatModel>> getChatList(@Header("Authorization") String Authorization);

    @GET("friend/list/detail/{id}")
    Call<List<DetailListFriendResponse>> getDetailFriendList(@Path("id") int id);

    @GET("chat/message/list/{id}")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<List<LastMessageModel>> getChatMessageList(@Header("Authorization") String Authorization, @Path("id") int id);

    @POST("chat/message/send")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<SendMessageResponse> sendChat(@Header("Authorization") String Authorization, @Body SendMessageModel body);

    @POST("chat/room/new")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<CreateChatRoomResponse> createChatRoom(@Header("Authorization") String Authorization, @Body CreateChatRoomModel body);

    @GET("chat/room/detail/{id}")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<RoomDetailResponse> getRoomDetail(@Header("Authorization") String Authorization, @Path("id") int id);

    @PATCH("chat/room/update/{id}")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<CreateChatRoomResponse> updateChatRoom(@Header("Authorization") String Authorization, @Body UpdateChatRoomModel body, @Path("id") int id);

    @POST("chat/room/new")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<DetailListFriendResponse> addFriend(@Header("Authorization") String Authorization, @Body AddFriendModel body);
}
