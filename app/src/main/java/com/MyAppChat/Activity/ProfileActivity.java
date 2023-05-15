package com.MyAppChat.Activity;

import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Model.PasswordModel;
import com.MyAppChat.Model.UpdateProfileModal;
import com.MyAppChat.Utils.ListFriendResponse;
import com.MyAppChat.Utils.ProfileResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myappchat.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends Fragment {
    TextView tvUsername;
    TextView tvFriends;
    EditText edtFN;
    EditText edtLN;
    EditText edtBday;
    EditText edtGender;
    Button btnEdit, btnSave;
    ImageView imgAvatar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        Bundle args = getArguments();
        tvUsername = view.findViewById(R.id.tvUsername);
        tvFriends = view.findViewById(R.id.tvFriends);
        edtFN = (EditText) view.findViewById(R.id.edtFN);
        edtLN = (EditText) view.findViewById(R.id.edtLN);
        edtBday = (EditText) view.findViewById(R.id.edtBday);
        edtGender = (EditText) view.findViewById(R.id.edtGender);
        btnEdit = (Button) view.findViewById(R.id.buttonEdit);
        btnSave = view.findViewById(R.id.buttonSave);
        imgAvatar = view.findViewById(R.id.profile_image);
        //enabled editting
        edtFN.setEnabled(false);
        edtLN.setEnabled(false);
        edtBday.setEnabled(false);
        edtGender.setEnabled(false);
        btnSave.setVisibility(View.INVISIBLE);
        //click button edit
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtFN.setEnabled(true);
                edtLN.setEnabled(true);
                edtBday.setEnabled(true);
                edtGender.setEnabled(true);
                //ẩn button edit
                btnEdit.setVisibility(View.INVISIBLE);
                //hiện button save
                btnSave.setVisibility(View.VISIBLE);
            }
        });

        int id = 0;
        String accessToken = "";
        Log.d("accessToken", accessToken);
        if (args != null) {
            id = args.getInt("id");
            accessToken = args.getString("access");
            // xử lý dữ liệu tại đây
        }
        String finalAccessToken = accessToken;
        Log.d("accessTokenSetting", finalAccessToken);
        //setText
        ApiService apiService = ApiClient.getApiService();
        apiService.getProfile(id).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.body() != null) {
                    tvUsername.setText(response.body().getUsername());
                    edtFN.setText(response.body().getFirst_name());
                    edtLN.setText(response.body().getLast_name());
                    edtBday.setText(response.body().getBirthday());
                    edtGender.setText(response.body().getGender());
                    Glide.with(view).load(response.body().getAvatar()).apply(RequestOptions.circleCropTransform()).override(350, 350).into(imgAvatar);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
        apiService.getFriendList(id).enqueue(new Callback<List<ListFriendResponse>>() {
            @Override
            public void onResponse(Call<List<ListFriendResponse>> call, Response<List<ListFriendResponse>> response) {
                if (response.body() != null) {
                    //đã fix
                    tvFriends.setText(String.valueOf(response.body().size()));
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ListFriendResponse>> call, Throwable t) {

            }
        });
        //enable edit when click button Edit

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = edtFN.getText().toString();
                String last_name = edtLN.getText().toString();
                String gender = edtGender.getText().toString();
                String bday = edtBday.getText().toString();

                if (first_name.isEmpty() || last_name.isEmpty() || gender.isEmpty() || bday.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "All filed are required.", Toast.LENGTH_SHORT).show();
                } else {
                    String jsonString = String.format("{\"first_name\":\"" + first_name + "\"," + "\"last_name\":\"" + last_name + "\"," + "\"gender\":\"" + gender + "\"," + "\"birthday\":\"" + bday + "\"}");

                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();
                    UpdateProfileModal updateProfileModal = gson.fromJson(jsonString, UpdateProfileModal.class);
//Log.d("abcd", String.valueOf(updateProfileModal));
//                    jsonString = gson.toJson(updateProfileModal);
//                    Log.d("abcd", jsonString);
                    apiService.updateProfile("Bearer " + finalAccessToken, updateProfileModal).enqueue(new Callback<ProfileResponse>() {
                        @Override
                        public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                            if (response.body() != null) {
                                String test = response.body().getFirst_name();
                                Log.d("abc", test);
                            } else Log.d("error", "loi");
                        }

                        @Override
                        public void onFailure(Call<ProfileResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });
        return view;
    }

}