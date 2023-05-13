package com.MyAppChat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MyAppChat.APIClient.ApiClient;
import com.MyAppChat.APIService.ApiService;
import com.MyAppChat.Utils.ProfileResponse;
import com.example.myappchat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends Fragment {
    TextView userId;

    EditText edtFN;
    EditText edtLN;
    EditText edtBday;
    EditText edtGender;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        Bundle args = getArguments();
        userId = view.findViewById(R.id.profile_username);
        edtFN = (EditText) view.findViewById(R.id.edtFN);
        edtLN = (EditText) view.findViewById(R.id.edtLN);
        edtBday = (EditText) view.findViewById(R.id.edtBday);
        edtGender = (EditText) view.findViewById(R.id.edtGender);
        int id = 0;
        if (args != null) {
            id = args.getInt("id");
            // xử lý dữ liệu tại đây
        }
        //setText
        ApiService apiService = ApiClient.getApiService();
        apiService.getProfile(id).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.body() != null) {
                    edtFN.setText(response.body().getFirst_name());
                    edtLN.setText(response.body().getLast_name());
                    edtBday.setText(response.body().getBirthday());
                    edtGender.setText(response.body().getGender());
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
        return view;
    }
}