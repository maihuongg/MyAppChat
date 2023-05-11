package com.MyAppChat.APIClient;

import com.MyAppChat.APIService.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApiClient  extends BaseClient{
    private static final String BASE_URL_User= "http:/localhost:8000/user/";
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            return createService(ApiService.class, BASE_URL_User);
        }
        return apiService;
    }
    Gson gson= new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
}
